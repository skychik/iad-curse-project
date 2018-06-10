// TODO: number of news reposts should have different icon from number of repost reposts.
// TODO: Number of news reposts contains number of it and repost reposts
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'
import {Button, Col, Glyphicon, Label, PageHeader, Panel, Row} from "react-bootstrap";
import UserPhoto from "./UserPhoto";
import DateTime from "./DateTime";
import {Link} from "react-router-dom";
import ActionButton from "./ActionButton";
import CommentModal from "./CommentModal";

export default class CourseTaskPreview extends Component {
    render() {
        const { taskId, taskTitle, courseId, courseTitle, courseType, authorId, authorUsername,
            authorAvatar, contentPreview, creationDate, alteringDate, commentsNumber,
            loopsNumber, loopWasPut, poopsNumber, poopWasPut, wasCompleted, putLoopOnTaskId, putPoopOnTaskId,
            removeLoopOnTaskId, removePoopOnTaskId} = this.props;

        if (taskId == null) return <h1>This news doesn't exist(no such news number)</h1>;

        const completeCourseTask = () => this.props.completeCourseTask(taskId);
        const undoCourseTask = () => this.props.undoCourseTask(taskId);
        const markdown = <ReactMarkdown source={contentPreview} className="markdown-container"/>;

        return <div>
            <Button bsStyle={wasCompleted ? "danger" : "success"}
                    onClick={wasCompleted ? undoCourseTask : completeCourseTask} bsSize="xs"
                    style={{marginLeft: "16px", borderRadius: "5px", width: "48px"}}>
                {wasCompleted ? "Undo" : "Done"}
            </Button>
            <Link to={"/id/"+ authorId + "/courses/" + courseId} style={{marginLeft: "20px", marginRight: "10px"}}>{courseTitle}</Link>
            <Label style={{top: "-3px"}}>{courseType}</Label>
            <Panel>
                <Panel.Heading>
                    <Row>
                        <div style={{display: "block", width: "79px", float: "left", padding: "0 15px 0 15px"}}>
                            <UserPhoto userId={authorId} username={authorUsername} withTooltip={true}
                                       photo={authorAvatar} size={48}/>
                        </div>
                        <h3 className="news_preview_title" style={{margin: "13px 94px 2.5px 20px"}}>
                            {taskTitle}
                        </h3>
                    </Row>
                </Panel.Heading>
                <Panel.Body style={{padding: "0"}}>
                    <div id={"wrapper"} style={{margin: "0"}}>{markdown}</div>
                </Panel.Body>
                <Panel.Footer>
                    <div>
                        <span className={"my_button"} style={{border: "none", color: "#aca7b9", cursor: "default"}}>
                            <Glyphicon glyph="comment" />
                            <span style={{marginLeft: "0", border: "none"}}>{commentsNumber}</span>
                        </span>

                        <ActionButton isLoop={false} putAction={putPoopOnTaskId}
                                      removeAction={removePoopOnTaskId} counter={poopsNumber}
                                      tooltip={"Put your Poop ;("} float={"right"} wasPut={poopWasPut}/>
                        <ActionButton isLoop={true} putAction={putLoopOnTaskId}
                                      removeAction={removeLoopOnTaskId} counter={loopsNumber}
                                      tooltip={"Put your Loop :)"} float={"right"} wasPut={loopWasPut}/>
                        <span className={"my_date"} style={{float: "right"}}>
                            <span style={{marginRight: 10}}>
                                <DateTime date={alteringDate == null ? creationDate : alteringDate} />
                            </span>
                        </span>
                    </div>
                </Panel.Footer>
            </Panel>
        </div>;
    }
}

CourseTaskPreview.propTypes = {
    taskId: PropTypes.number.isRequired,
    taskTitle: PropTypes.string.isRequired,
    courseId: PropTypes.number.isRequired,
    courseTitle: PropTypes.string.isRequired,
    courseType: PropTypes.string.isRequired,
    authorId: PropTypes.number.isRequired,
    authorUsername: PropTypes.string.isRequired,
    authorAvatar: PropTypes.string.isRequired,
    contentPreview: PropTypes.string.isRequired,
    creationDate: PropTypes.string.isRequired,
    alteringDate: PropTypes.string,
    commentsNumber: PropTypes.number,
    loopsNumber: PropTypes.number.isRequired,
    loopWasPut: PropTypes.bool.isRequired,
    poopsNumber: PropTypes.number.isRequired,
    poopWasPut: PropTypes.bool.isRequired,
    wasCompleted: PropTypes.bool.isRequired,
    putLoopOnTaskId: PropTypes.func.isRequired,
    putPoopOnTaskId: PropTypes.func.isRequired,
    removeLoopOnTaskId: PropTypes.func.isRequired,
    removePoopOnTaskId: PropTypes.func.isRequired,
    completeCourseTask: PropTypes.func.isRequired,
    undoCourseTask: PropTypes.func.isRequired,
};