import React from 'react';
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import News from "../components/News";
import CommentsContainer from "./CommentsContainer";
import ActionButton from "../components/ActionButton";
import {Button} from "react-bootstrap";
import CommentModal from "../components/CommentModal";

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
        const type = this.props.match;
        console.log("type:");
        console.log(type);
    }

    setNews() {
        const number = parseInt(this.props.match.params.number, 10);
        this.props.setNews(number.toString());
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
        if (!this.props.news || !this.props.news.author) {
            return null;
        }
        //console.log("NewsContainer.render");
        const {actionButton} = this.props;
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
                {newsData.pending ? "Loading..."
                    : <News className="News"
                            newsId={newsData.id}
                            authorId={newsData.author.id}
                            authorUsername={newsData.author.username}
                            authorAvatar={newsData.author.photo}
                            title={newsData.title}
                            content={newsData.content}
                            creationDate={newsData.creationDate}
                            alteringDate={newsData.alteringDate}/>}
                <div className="news_footer">
                    <span className="news_footer_comments">
                        Comments <span>{newsData.commentsNumber}</span>
                    </span>
                    <ActionButton isLoop={false} action={putPoopOnNewsId} alternativeAction={removePoopOnNewsId}
                                  counter={newsData.poopsNumber} tooltip={"Put your Poop ;("} float={"right"}
                                  wasPut={newsData.poopWasPut} pending={actionButton.pending}/>
                    <ActionButton isLoop={true} action={putLoopOnNewsId} alternativeAction={removeLoopOnNewsId}
                                  counter={newsData.loopsNumber} tooltip={"Put your Loop :)"} float={"right"}
                                  wasPut={newsData.loopWasPut} pending={actionButton.pending}/>
                </div>
                <CommentsContainer newsId={newsData.id}/>
                <Button bsStyle="primary" bsSize="large" onClick={showAddNewComment}
                        style={{float: "right", borderRadius: "5px"}}>
                    Add new comment
                </Button>

                <CommentModal comment={commentingComm} isShown={newsData.addCommentShowed} onHide={this.props.hideAddComment}
                              onChange={this.handleChange} onSubmit={this.handleSubmitNewComment}
                              content={this.props.news.newCommentContent} />
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        news: state.news,
        actionButton: state.actionButton,
    }
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(NewsContainer);