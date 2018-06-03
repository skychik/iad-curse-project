import React from 'react';
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import News from "../components/News";
import CommentsContainer from "./CommentsContainer";
import PLoopButton from "../components/PLoopButton";
import {Button, ControlLabel, Form, FormControl, FormGroup, Modal, Panel, Well} from "react-bootstrap";
import Comment from "../components/Comment";
import UserPhoto from "../components/UserPhoto";
import DateTime from "../components/DateTime";

// props:
//  news - News in JSON
class NewsContainer extends React.Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmitNewComment = this.handleSubmitNewComment.bind(this);
    }

    componentDidMount() {
        this.setNews();
    }

    setNews() {
        const number = parseInt(this.props.match.params.number, 10);
        if (isNaN(number)) {
            this.props.setNews(null);
        } else {
            this.props.setNews(number.toString());
        }
    }

    handleChange(event) {
        switch (event.target.name) {
            case "content":
                this.props.setNewCommentContent(event.target.value); break;
        }
    }

    handleSubmitNewComment(event) {
        const news = this.props.news;
        if (this.props.news.newCommentContent) {
            console.log("newComment");
            console.log({content: news.newCommentContent, newsId : news.commentingComm.newsId, onCommentId: news.commentingComm.id == null ? "" : news.commentingComm.id});
            this.props.addNewComment({content: news.newCommentContent, newsId : news.commentingComm.newsId,
                onCommentId: news.commentingComm.id == null ? "" : news.commentingComm.id});
        }

        event.preventDefault();
    }

    render() {
        if (this.props.news === null) {
            return null;
        }
        //console.log("NewsContainer.render");
        const newsData = this.props.news;
        console.log('---data:---');
        console.log(newsData);
        console.log('---data---');
        const commentingComm = this.props.news.commentingComm;
        console.log("commentingComm:");
        console.log(commentingComm);

        if (newsData == null) {
            // console.log("render:null");
            return <h1>This news doesn't exist(incorrect news number)</h1>
        }
        if (Object.keys(newsData).length === 0) {
            return <h1>Loading</h1>
        }

        const putLoopOnNewsId = () => this.props.putLoopOnNewsId(newsData.id);
        const putPoopOnNewsId = () => this.props.putPoopOnNewsId(newsData.id);
        const removeLoopOnNewsId = () => this.props.removeLoopOnNewsId(newsData.id);
        const removePoopOnNewsId = () => this.props.removePoopOnNewsId(newsData.id);
        const showAddNewComment = () => this.props.showAddComment({newsId: newsData.id, content: null});

        return (
            <div className="News_container">
                <News className="News"
                      newsId={newsData.id}
                      authorId={newsData.author.id}
                      authorUsername={newsData.author.username}
                      authorAvatar={newsData.author.photo}
                      title={newsData.title}
                      content={newsData.content}
                      creationDate={newsData.creationDate}
                      alteringDate={newsData.alteringDate}/>
                <div className="news_footer">
                    <span className="news_footer_comments">
                        Comments <span>{newsData.commentsNumber}</span>
                    </span>
                    <PLoopButton isLoop={false} putAction={putPoopOnNewsId} removeAction={removePoopOnNewsId}
                                 counter={newsData.poopsNumber} tooltip={"Put your Poop ;("} float={"right"}
                                 wasPut={newsData.poopWasPut}/>
                    <PLoopButton isLoop={true} putAction={putLoopOnNewsId} removeAction={removeLoopOnNewsId}
                                 counter={newsData.loopsNumber} tooltip={"Put your Loop :)"} float={"right"}
                                 wasPut={newsData.loopWasPut}/>
                </div>
                <CommentsContainer newsId={newsData.id}/>
                <Button bsStyle="primary" bsSize="large" onClick={showAddNewComment}
                        style={{float: "right", borderRadius: "5px"}}>
                    Add new comment
                </Button>
                <Modal show={newsData.addCommentShowed} onHide={this.props.hideAddComment}
                       bsSize="large" aria-labelledby="contained-modal-title-lg">
                    <Form onSubmit={this.handleSubmitNewComment}>
                        <Modal.Header closeButton>
                            <Modal.Title id="contained-modal-title-sm">Add comment</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <FormGroup controlId="formControlsTextarea">
                                {(commentingComm !== null) ? (commentingComm.content !== null) ?
                                    <div>
                                        <UserPhoto userId={commentingComm.userId} username={commentingComm.username}
                                                   withTooltip={false} photo={commentingComm.avatar} />
                                        <DateTime date={commentingComm.creationDate} />
                                        <Well style={{overflow: "hidden", textOverflow: "ellipsis"}}>
                                            {commentingComm.content}
                                        </Well>
                                    </div> : null : null}
                                <FormControl componentClass="textarea" placeholder="Write your comment..." name="content"
                                             style={{resize: "none"}} rows="10" onChange={this.handleChange}/>
                            </FormGroup>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button type="submit">Send</Button>
                            <Button onClick={this.props.hideAddComment}>Close</Button>
                        </Modal.Footer>
                    </Form>
                </Modal>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        news: state.news,
    }
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(NewsContainer);