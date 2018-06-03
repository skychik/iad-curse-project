import React, { Component } from 'react'
import PropTypes from 'prop-types'
import UserPhoto from "./UserPhoto";
import DateTime from "./DateTime";
import PLoopButton from "./PLoopButton";
import {Button, Glyphicon} from "react-bootstrap";

export default class Comment extends Component {
    render() {
        const { comments, userId, username, content, creationDate, loopsNumber, loopWasPut, poopsNumber, poopWasPut, avatar} = this.props;

        const putLoopOnCommentId = () => this.props.putLoopOnCommentId(this.props.id);
        const putPoopOnCommentId = () => this.props.putPoopOnCommentId(this.props.id);
        const removeLoopOnCommentId = () => this.props.removeLoopOnCommentId(this.props.id);
        const removePoopOnCommentId = () => this.props.removePoopOnCommentId(this.props.id);
        const showAddComment = () => this.props.showAddComment({...this.props, comments: null});

        return <ul className="comment">
                <div className="comment_head">
                    <UserPhoto userId={userId} username={username} withTooltip={false} photo={avatar} />
                    {/*<a href={"/id" + userId}>
                        <span className="comment_img_container">
                            <img src={"/photo/usr/icon" + userId + ".jpg"}
                                 onError="this.onerror=null; this.src=/photo/usr/default.png" />
                        </span>
                        <span className="comment_username">{username}</span>
                    </a>*/}
                    <DateTime date={creationDate} />
                    <PLoopButton isLoop={false} putAction={putPoopOnCommentId} removeAction={removePoopOnCommentId}
                                 counter={poopsNumber} tooltip={"Put your Poop ;("} float={"right"} wasPut={poopWasPut}/>
                    <PLoopButton isLoop={true} putAction={putLoopOnCommentId} removeAction={removeLoopOnCommentId}
                                 counter={loopsNumber} tooltip={"Put your Loop :)"} float={"right"} wasPut={loopWasPut}/>
                    <Button onClick={showAddComment} bsStyle="default" bsSize="xsmall"
                            style={{float: "right", borderRadius: "5px", backgroundColor: "#f6f6f6"}}>
                        <Glyphicon glyph="comment" style={{top: "2.5px", color: "#555555"}}/>
                    </Button>
                </div>
                {/*<span className="my_comment_dot" >•</span>*/}
                <span className="my_text">
                    {content}
                </span>
                <div className="comment_children">
                    {comments.map((c, idx) => {
                        return <li key={idx}>
                            {/*<span className="my_comment_dot" >•</span>*/}
                            <Comment comments={c.comments}
                                     id={c.id}
                                     newsId={c.newsId}
                                     userId={c.user.id}
                                     username={c.user.username}
                                     avatar={c.user.photo}
                                     content={c.content}
                                     creationDate={c.creationDate}
                                     loopsNumber={c.loopsNumber}
                                     loopWasPut={c.loopWasPut}
                                     poopsNumber={c.poopsNumber}
                                     poopWasPut={c.poopWasPut}
                                     putLoopOnCommentId={this.props.putLoopOnCommentId}
                                     putPoopOnCommentId={this.props.putPoopOnCommentId}
                                     removeLoopOnCommentId={this.props.removeLoopOnCommentId}
                                     removePoopOnCommentId={this.props.removePoopOnCommentId}
                                     showAddComment={this.props.showAddComment}/>
                        </li>})}
                </div>
            </ul>
    }
}

Comment.propTypes = {
    id: PropTypes.number.isRequired,
    comments: PropTypes.array,
    newsId: PropTypes.number.isRequired,
    userId: PropTypes.number.isRequired,
    username: PropTypes.string.isRequired,
    avatar: PropTypes.string.isRequired,
    content: PropTypes.string.isRequired,
    creationDate: PropTypes.string.isRequired,
    loopsNumber: PropTypes.number.isRequired,
    loopWasPut: PropTypes.bool.isRequired,
    poopsNumber: PropTypes.number.isRequired,
    poopWasPut: PropTypes.bool.isRequired,
    putLoopOnCommentId: PropTypes.func.isRequired,
    putPoopOnCommentId: PropTypes.func.isRequired,
    removeLoopOnCommentId: PropTypes.func.isRequired,
    removePoopOnCommentId: PropTypes.func.isRequired,
    showAddComment: PropTypes.func.isRequired,
};