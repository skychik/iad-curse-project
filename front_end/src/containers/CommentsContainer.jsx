import React from 'react';
import RestClient from "another-rest-client";
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import News from "../components/News";
import {Redirect} from "react-router-dom";
import Comment from "../components/Comment";
import {PageHeader} from "react-bootstrap";

class CommentsContainer extends React.Component {
    componentDidMount() {
        let api = new RestClient('http://localhost:8080'); // TODO: make static
        //console.log(this.props.match.params.number);
        // const number = parseInt(this.props.match.params.number, 10);
        // if (isNaN(number)) {
        //     console.log("didMount:NaN");
        //     this.setNews(null);
        //     return;
        // }
        // console.log("didMount:not NaN");

        const promise = api.res("comments").res("for").get({newsId:this.props.newsId});
        promise.then((response) => {
            const news = JSON.parse(JSON.stringify(response));
            // console.log(news);
            this.setComments(news);
        });
    }

    setComments(comments) {
        // console.log("comments: ");
        // console.log(comments);
        return this.props.setComments(comments);
    }

    render() {
        const commentsData = this.props.comments;
        const total = commentsData.total;
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
                                content={comment.content}
                                creationDate={comment.creationDate}
                                loopsNumber={comment.loopsNumber}
                                poopsNumber={comment.poopsNumber}/>
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