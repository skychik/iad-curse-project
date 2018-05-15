import React, { Component } from 'react'
import PropTypes from 'prop-types'
import {Glyphicon, OverlayTrigger, Tooltip} from "react-bootstrap";
import UserPhoto from "./UserPhoto";
import DateTime from "./DateTime";
import {Link} from "react-router-dom";
import PLoopButton from "./PLoopButton";

export default class Comment extends Component {
    putPoopOnCommentId(commentId) {
        return this.props.putPoopOnCommentId(commentId)
    }

    putLoopOnCommentId(commentId) {
        return this.props.putLoopOnCommentId(commentId)
    }

    render() {
        const { comments, userId, username, content, creationDate, loopsNumber, poopsNumber, avatar} = this.props;

        const loopTip = (<Tooltip id="tooltip_username">Put your Loop :)</Tooltip>);
        const poopTip = (<Tooltip id="tooltip_username">Put your Poop ;(</Tooltip>);

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
                    <PLoopButton isLoop={false} action={this.putPoopOnCommentId(this.props.id)} counter={poopsNumber} tooltip={"Put your Poop ;("} float={"right"}/>
                    <PLoopButton isLoop={true} action={this.putLoopOnCommentId(this.props.id)} counter={loopsNumber} tooltip={"Put your Loop :)"} float={"right"}/>
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
                                     poopsNumber={c.poopsNumber}/>
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
    poopsNumber: PropTypes.number.isRequired,
};