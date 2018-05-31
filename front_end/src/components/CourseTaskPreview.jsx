// TODO: number of news reposts should have different icon from number of repost reposts.
// TODO: Number of news reposts contains number of it and repost reposts
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'
import {PageHeader, Panel, Row} from "react-bootstrap";
import UserPhoto from "./UserPhoto";
import DateTime from "./DateTime";
import {Link} from "react-router-dom";
import PLoopButton from "./PLoopButton";

export default class CourseTaskPreview extends Component {
    render() {
        const { taskId, taskTitle, courseId, courseTitle, authorId, authorUsername,
            authorAvatar, contentPreview, creationDate, alteringDate, commentsNumber,
            loopsNumber, loopWasPut, poopsNumber, poopWasPut, putLoopOnTaskId, putPoopOnTaskId,
            removeLoopOnTaskId, removePoopOnTaskId,} = this.props;

        if (this.id == null) return <h1>This news doesn't exist(no such news number)</h1>;

        const markdown = <ReactMarkdown source={contentPreview} />;

        return <Panel>
            <Panel.Heading>
                <Row>
                    <Link to={"/id/"+ authorId + "/courses/" + courseId}>{courseTitle}</Link>
                    <span>Done/Undone</span>
                </Row>
                <Row>
                    <div style={{display: "block", width: "74px", float: "right"}}>
                        <UserPhoto userId={authorId} username={authorUsername} withTooltip={true}
                                   photo={authorAvatar} size={64}/>
                    </div>
                    <div style={{display: "block", marginRight: "94px"}}>
                        <h3 className="news_preview_title">
                            {taskTitle}
                        </h3>
                    </div>
                </Row>
            </Panel.Heading>
            <Panel.Body style={{paddingTop: "0", paddingBottom: "0"}}>
                <div id={"wrapper"}>{markdown}</div>
            </Panel.Body>
            <Panel.Footer>
                <div>
                    <span className={"my_button"}>
                        Comments
                        <span>{commentsNumber}</span>
                    </span>

                    <PLoopButton isLoop={false} putAction={putPoopOnTaskId}
                                 removeAction={removePoopOnTaskId} counter={poopsNumber}
                                 tooltip={"Put your Poop ;("} float={"right"} wasPut={poopWasPut}/>
                    <PLoopButton isLoop={true} putAction={putLoopOnTaskId}
                                 removeAction={removeLoopOnTaskId} counter={loopsNumber}
                                 tooltip={"Put your Loop :)"} float={"right"} wasPut={loopWasPut}/>
                    <span className={"my_date"} style={{float: "right"}}>
                        <span style={{marginRight: 10}}>
                            <DateTime date={alteringDate == null ? creationDate : alteringDate} />
                        </span>
                    </span>
                </div>
            </Panel.Footer>
        </Panel>;
    }
}

CourseTaskPreview.propTypes = {
    taskId: PropTypes.number.isRequired,
    taskTitle: PropTypes.string.isRequired,
    courseId: PropTypes.number.isRequired,
    courseTitle: PropTypes.string.isRequired,
    authorId: PropTypes.number.isRequired,
    authorUsername: PropTypes.string.isRequired,
    authorAvatar: PropTypes.string.isRequired,
    contentPreview: PropTypes.string.isRequired,
    creationDate: PropTypes.string.isRequired,
    alteringDate: PropTypes.string,
    commentsNumber: PropTypes.number.isRequired,
    loopsNumber: PropTypes.number.isRequired,
    loopWasPut: PropTypes.bool.isRequired,
    poopsNumber: PropTypes.number.isRequired,
    poopWasPut: PropTypes.bool.isRequired,
    putLoopOnTaskId: PropTypes.func.isRequired,
    putPoopOnTaskId: PropTypes.func.isRequired,
    removeLoopOnTaskId: PropTypes.func.isRequired,
    removePoopOnTaskId: PropTypes.func.isRequired,
};