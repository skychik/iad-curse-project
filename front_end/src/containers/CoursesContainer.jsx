// TODO: number of news reposts should have different icon from number of repost reposts.
// TODO: Number of news reposts contains number of it and repost reposts
import React from 'react';
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import CourseTaskPreview from "../components/CourseTaskPreview";
import {
    Button,
    ButtonGroup,
    ButtonToolbar, Col,
    DropdownButton,
    MenuItem,
    PageHeader, Row, ToggleButton,
    ToggleButtonGroup
} from "react-bootstrap";
import {Redirect} from "react-router-dom";

class CoursesContainer extends React.Component {
    componentWillMount() {
        this.props.setCourseBackground(true);
        this.props.setCourseTypes();
        const type = this.props.match.params.type;
        if (!type) {
            this.props.setCourseTasks();
        } else {
            this.props.setCourseTasksByType(type);
        }
    }

    render() {
        const {courseTypes, actionButton} = this.props;
        const {type} = this.props.match.params;

        if (courseTypes.correct === false) {
            alert(courseTypes.answer);
        }

        if (type && courseTypes.correct) {
            if (courseTypes.types.indexOf(type) === -1) {
                return <Redirect to="/courses"/>;
            }
        }

        const courseTaskList = this.props.courseTaskList;

        if (courseTaskList == null) {
            return <h1>No courses</h1>
        }

        const taskListContainer = courseTaskList != null ?
            courseTaskList.map((task, idx) => {
                const putLoopOnTaskId = () => this.props.putLoopOnTaskId(task.id);
                const putPoopOnTaskId = () => this.props.putPoopOnTaskId(task.id);
                const removeLoopOnTaskId = () => this.props.removeLoopOnTaskId(task.id);
                const removePoopOnTaskId = () => this.props.removePoopOnTaskId(task.id);
                const completeCourseTask = () => this.props.completeCourseTask(task.id);
                const undoCourseTask = () => this.props.undoCourseTask(task.id);

                return <CourseTaskPreview className="CourseTaskPreview"
                                          key={idx}
                                          taskId={task.id}
                                          taskTitle={task.taskTitle}
                                          courseId={task.course.id}
                                          courseTitle={task.course.title}
                                          courseType={task.course.type}
                                          authorId={task.course.author.id}
                                          authorUsername={task.course.author.username}
                                          authorAvatar={task.course.author.photo}
                                          contentPreview={task.contentPreview}
                                          creationDate={task.creationDate}
                                          alteringDate={task.alteringDate}
                                          loopsNumber={task.loopsNumber}
                                          loopWasPut={task.loopWasPut}
                                          poopsNumber={task.poopsNumber}
                                          poopWasPut={task.poopWasPut}
                                          wasCompleted={task.wasCompleted}
                                          putLoopOnTaskId={putLoopOnTaskId}
                                          putPoopOnTaskId={putPoopOnTaskId}
                                          removeLoopOnTaskId={removeLoopOnTaskId}
                                          removePoopOnTaskId={removePoopOnTaskId}
                                          completeCourseTask={completeCourseTask}
                                          undoCourseTask={undoCourseTask}
                                          pending={actionButton.pending}/>;
            }) : "No courses";

        return <div className="CoursesContainer">
            <PageHeader style={{textAlign: "center"}}>Course Tasks</PageHeader>

            <div style={{display: "table-cell", width: "15px"}}/>
            <ButtonGroup style={{margin: "15px", display: "table-cell", textAlign: "center", verticalAlign: "middle"}}>
                <Button disabled={!type} href="/courses">all</Button>
                {courseTypes.types ?
                    <DropdownButton title="type" id="bg-justified-dropdown">
                        {type ?
                            courseTypes.types.map((t, idx) =>
                                <MenuItem key={idx} eventKey={idx} href={"/courses/type/" + t}
                                          disabled={t === type}>
                                    <span style={{color: t === type ? "#ccc" : ""}}>
                                        {t}
                                    </span>
                                </MenuItem>)
                            : courseTypes.types.map((type, idx) =>
                            <MenuItem key={idx} eventKey={idx} href={"/courses/type/" + type}>{type}</MenuItem>)}
                    </DropdownButton> : ""}
            </ButtonGroup>
            <div style={{display: "table-cell", width: "25px"}}/>
            <div style={{display: "table-cell", textAlign: "center", verticalAlign: "middle"}}>
                <h3 style={{margin: "0"}}>
                    {!type ? "All Courses" : type[0].toUpperCase() + type.substring(1) + " Courses"}
                </h3>
            </div>

            <div style={{marginTop: "26px"}}>
                {taskListContainer.length === 0 ?
                    <span className={"profile-no-news"}>No courses</span>
                    : taskListContainer}
            </div>
        </div>;
    }
}

const mapStateToProps = (state) => {
    return {
        courseTaskList: state.courseTaskList,
        courseTypes: state.courseTypes,
        actionButton: state.actionButton,
    }
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(CoursesContainer);