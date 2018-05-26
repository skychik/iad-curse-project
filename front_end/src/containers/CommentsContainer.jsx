import React from 'react';
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import Comment from "../components/Comment";

class CommentsContainer extends React.Component {
    componentDidMount() {
        this.props.setComments(this.props.newsId)
    }

    render() {
        if (!this.props.comments) return "Loading...";

        const commentsData = this.props.comments;
        console.log("Comments container commentsData:");
        console.log(commentsData);

        if (Object.keys(commentsData).length === 0) {
            return "No comments";
        }

        return <div className="comments_container">
                {commentsData.comments.map((comment, idx) => {
                    return <Comment key={idx}
                                    comments={comment.comments}
                                    id={comment.id}
                                    newsId={comment.newsId}
                                    userId={comment.user.id}
                                    username={comment.user.username}
                                    avatar={comment.user.photo}
                                    content={comment.content}
                                    creationDate={comment.creationDate}
                                    loopsNumber={comment.loopsNumber}
                                    loopWasPut={comment.loopWasPut}
                                    putLoopOnCommentId={this.props.putLoopOnCommentId}
                                    poopsNumber={comment.poopsNumber}
                                    poopWasPut={comment.poopWasPut}
                                    putPoopOnCommentId={this.props.putPoopOnCommentId}
                                    removeLoopOnCommentId={this.props.removeLoopOnCommentId}
                                    removePoopOnCommentId={this.props.removePoopOnCommentId}/>
                })}
            </div>
    }
}

const mapStateToProps = (state) => {
    return {comments: state.comments}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(CommentsContainer);