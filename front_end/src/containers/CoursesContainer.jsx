// TODO: number of news reposts should have different icon from number of repost reposts.
// TODO: Number of news reposts contains number of it and repost reposts
import React from 'react';
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import CourseTaskPreview from "../components/CourseTaskPreview";
import {PageHeader} from "react-bootstrap";

class CoursesContainer extends React.Component {
    componentWillMount() {
        this.props.setCourseBackground(true);
        this.props.setCourseTasks();
    }

    render() {
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
                const completeCourseTask = () => this.props.completeCourseTaskId(task.id);
                const undoCourseTask = () => this.props.undoCourseTaskId(task.id);

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
                                          undoCourseTask={undoCourseTask}/>;
            }) : "No courses";

        return (
            <div className="CoursesContainer">
                <PageHeader style={{textAlign: "center"}}>Course Tasks</PageHeader>
                {taskListContainer.length === 0 ?
                    <span className={"profile-no-news"}>No courses</span>
                    : taskListContainer}
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {courseTaskList: state.courseTaskList}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(CoursesContainer);