import React from 'react';
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import NewsPreview from "../components/NewsPreview";
import {Glyphicon, OverlayTrigger, PageHeader, Tooltip} from "react-bootstrap";
import CommentModal from "../components/CommentModal";

// props:
//  news - News in JSON
class FeedContainer extends React.Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmitNewComment = this.handleSubmitNewComment.bind(this);
    }

    componentDidMount() {
        this.props.setFeed();
    }

    shouldComponentUpdate() {
        // console.log("shouldComponentUpdate()");
        // this.props.feed.map((news, idx) => {
        //     news.authorPhoto = news.author.id
        // });
        return true;
    }

    handleChange(event) {
        switch (event.target.name) {
            case "content":
                this.props.setNewCommentContent(event.target.value); break;
        }
    }

    handleSubmitNewComment(event) {
        const feed = this.props.feed;
        if (this.props.feed.newCommentContent) {
            console.log("newComment");
            console.log({content: feed.newCommentContent, newsId : feed.commentingComm.newsId,
                onCommentId: feed.commentingComm.id == null ? "" : feed.commentingComm.id});
            this.props.addNewComment({content: feed.newCommentContent, newsId : feed.commentingComm.newsId,
                onCommentId: feed.commentingComm.id == null ? "" : feed.commentingComm.id});
        }

        event.preventDefault();
    }

    render() {
        const {actionButton} = this.props;

        if (!this.props.feed) {
            return "";
        }
        const data = this.props.feed.data;

        if (!data) {
            return "";
        }

        const feedContainer =  data != null ?
            data.map((newsPreview, idx) => {
                const putLoopOnNewsId = () => this.props.putLoopOnNewsId(newsPreview.id);
                const putPoopOnNewsId = () => this.props.putPoopOnNewsId(newsPreview.id);
                const removeLoopOnNewsId = () => this.props.removeLoopOnNewsId(newsPreview.id);
                const removePoopOnNewsId = () => this.props.removePoopOnNewsId(newsPreview.id);
                const showAddNewComment = () => this.props.showAddComment({newsId: newsPreview.id, content: null});

                return <NewsPreview className="NewsPreview"
                                    key={idx}
                                    newsId={newsPreview.id}
                                    authorId={newsPreview.author.id}
                                    authorUsername={newsPreview.author.username}
                                    authorAvatar={newsPreview.author.photo}
                                    title={newsPreview.title}
                                    contentPreview={newsPreview.contentPreview}
                                    creationDate={newsPreview.creationDate}
                                    alteringDate={newsPreview.alteringDate}
                                    commentsNumber={newsPreview.commentsNumber}
                                    loopsNumber={newsPreview.loopsNumber}
                                    loopWasPut={newsPreview.loopWasPut}
                                    poopsNumber={newsPreview.poopsNumber}
                                    poopWasPut={newsPreview.poopWasPut}
                                    putLoopOnNewsId={putLoopOnNewsId}
                                    putPoopOnNewsId={putPoopOnNewsId}
                                    removeLoopOnNewsId={removeLoopOnNewsId}
                                    removePoopOnNewsId={removePoopOnNewsId}
                                    showAddNewComment={showAddNewComment}
                                    pending={actionButton.pending}
                             />;
            }) : [];

        return(
            <div className="Feed_container">
                <PageHeader style={{textAlign: "center"}}>
                    Feed
                </PageHeader>
                <div>{feedContainer.length === 0 ? <span className={"feed-no-news"}>No news</span> : feedContainer}</div>

                <CommentModal isShown={this.props.feed.addCommentShowed} onHide={this.props.hideAddComment}
                              onChange={this.handleChange} onSubmit={this.handleSubmitNewComment}
                              content={this.props.feed.newCommentContent} />
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        feed: state.feed,
        actionButton: state.actionButton,
    }
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(FeedContainer);