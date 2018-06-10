// TODO: number of news reposts should have different icon from number of repost reposts.
// TODO: Number of news reposts contains number of it and repost reposts
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'
import Panel from "react-bootstrap/lib/Panel";
import {Button, Glyphicon, Row} from "react-bootstrap";
import UserPhoto from "./UserPhoto";
import DateTime from "./DateTime";
import {Link} from "react-router-dom";
import ActionButton from "./ActionButton";

export default class CoursePreview extends Component {

    render() {
        const { courseId, isSelf, creationDate, alteringDate, title, type, subscribed,
            commentsNumber, totalLoopsNumber, totalPoopsNumber, completionRate } = this.props;


        return <Panel>
            <div>
                {isSelf ? "" :
                    <Button onClick={subscribed ? this.props.unsubscribeCourseId : this.props.subscribeCourseId}
                            bsStyle={subscribed ? "warning" : "primary"}>
                        {subscribed ? "Unsubscribe" : "Subscribe"}
                    </Button>}
            </div>
            <Panel.Heading>
                <Row>
                    <div style={{display: "block", marginRight: "94px"}}>
                        <h3 className="news_preview_title">
                            {title}
                        </h3>
                    </div>
                </Row>
            </Panel.Heading>
            <Panel.Body style={{padding: "0"}}>
                <span className={"my_button"} style={{border: "none", color: "#aca7b9", cursor: "default"}}>
                    Loops
                    <span style={{marginLeft: "0", border: "none"}}>{totalLoopsNumber}</span>
                </span>
                <span className={"my_button"} style={{border: "none", color: "#aca7b9", cursor: "default"}}>
                    Poops
                    <span style={{marginLeft: "0", border: "none"}}>{totalPoopsNumber}</span>
                </span>
                {commentsNumber == null ? "" :
                    <span className={"my_button"} style={{border: "none", color: "#aca7b9", cursor: "default"}}>
                        <Glyphicon glyph="comment" />
                        <span style={{marginLeft: "0", border: "none"}}>{commentsNumber}</span>
                    </span>}
                <span className={"my_button"} style={{border: "none", color: "#aca7b9", cursor: "default"}}>
                    Completion Rate
                    <span style={{marginLeft: "0", border: "none"}}>
                        {completionRate.toString().length > 5 ? completionRate.toString().substr(0, 5) : completionRate}
                        </span>
                </span>

            </Panel.Body>
            <Panel.Footer>
                <div style={{height: "25.43px"}}>
                    <span className={"my_date"} style={{float: "right", paddingTop: "2.5px"}}>
                        <span style={{marginRight: 10}}>
                            <DateTime date={alteringDate == null ? creationDate : alteringDate} />
                        </span>
                    </span>
                </div>
            </Panel.Footer>
        </Panel>;
    }
}

CoursePreview.propTypes = {
    courseId: PropTypes.number.isRequired,
    isSelf: PropTypes.bool.isRequired,
    creationDate: PropTypes.string.isRequired,
    alteringDate: PropTypes.string,
    title: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
    commentsNumber: PropTypes.number,
    totalLoopsNumber: PropTypes.number.isRequired,
    totalPoopsNumber: PropTypes.number.isRequired,
    completionRate: PropTypes.number.isRequired,
    subscribed: PropTypes.bool,
    subscribeCourseId: PropTypes.func,
    unsubscribeCourseId: PropTypes.func,
};