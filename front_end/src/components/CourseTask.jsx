import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'
import {Button, Label, PageHeader, Panel, Row} from "react-bootstrap";
import UserPhoto from "./UserPhoto";
import DateTime from "./DateTime";
import {Link} from "react-router-dom";
import ActionButton from "./ActionButton";

export default class CourseTaskPreview extends Component {
    render() {
        const { taskId, taskTitle, courseId, courseTitle, courseType, authorId, authorUsername,
            authorAvatar, contentPreview, creationDate, alteringDate, commentsNumber,
            loopsNumber, loopWasPut, poopsNumber, poopWasPut, wasCompleted, putLoopOnTaskId, putPoopOnTaskId,
            removeLoopOnTaskId, removePoopOnTaskId} = this.props;

        if (taskId == null) return <h1>This news doesn't exist(no such news number)</h1>;

        const completeCourseTask = () => this.props.completeCourseTask(taskId);
        const undoCourseTask = () => this.props.undoCourseTask(taskId);
        const markdown = <ReactMarkdown source={contentPreview} />;

        return <div>
            <Button bsStyle={wasCompleted ? "danger" : "success"}
                    onClick={wasCompleted ? undoCourseTask : completeCourseTask} bsSize="s"
                    style={{marginLeft: "15px"}}>
                {wasCompleted ? "Undone" : "Done"}
            </Button>
            <Link to={"/id/"+ authorId + "/courses/" + courseId} style={{marginLeft: "20px", marginRight: "10px"}}>{courseTitle}</Link>
            <Label style={{top: "-3px"}}>{courseType}</Label>
            <Panel>
                <Panel.Heading>
                    <Row style={{marginLeft: "15px"}}>
                        <div style={{display: "block", width: "74px", float: "right"}}>
                            <UserPhoto userId={authorId} username={authorUsername} withTooltip={true}
                                       photo={authorAvatar} size={64}/>
                        </div>

                        <h3 className="news_preview_title">
                            {taskTitle}
                        </h3>
                    </Row>
                </Panel.Heading>
                <Panel.Body style={{padding: "0"}}>
                    <div id={"wrapper"} style={{margin: "0"}}>{markdown}</div>
                </Panel.Body>
                <Panel.Footer>
                    <div>
                        <span className={"my_button"}>
                            Comments
                            <span>{commentsNumber}</span>
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