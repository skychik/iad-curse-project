import * as actionCreators from "../actions";
import {connect} from "react-redux";
import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import {
    Button,
    Checkbox,
    Col,
    ControlLabel,
    Form,
    FormControl,
    FormGroup,
    Glyphicon, Grid,
    PageHeader,
    Row
} from "react-bootstrap";
import RestClient from "another-rest-client";
import cookie from "react-cookies";
import {bindActionCreators} from "redux";

// TODO: make validation for redactor

class NewsRedactorContainer extends React.Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentWillMount() {
        this.props.setCourseBackground(true);
        this.props.setCourses();
        this.props.setRedactorIsNewCourse(false);
        this.props.setCourseTypes();
    }

    setPostingNewsAnswer(response) {
        this.props.setPostingNewsAnswer(response);
    }

    handleChange(event) {
        switch (event.target.name) {
            case "task_title":
                this.props.setRedactorTaskTitle(event.target.value); break;
            case "content":
                this.props.setRedactorContent(event.target.value); break;
            case "select_is_new_course":
                this.props.setRedactorIsNewCourse(event.target.checked); break;
            case "select_course":
                this.props.redactor.userCourses.map((course => {
                    if (course.title === event.target.value) {
                        this.props.setCourseId(course.id);
                    }
                }));
                break;
            case "course_title":
                this.props.setCourseTitle(event.target.value); break;
            case "select_course_type":
                if (event.target.value) {
                    if (event.target.value === "other") {
                        this.props.setCourseType(null); break;
                    } else {
                        this.props.setCourseType(event.target.value); break;
                    }
                }
        }
    }

    handleSubmit(event) {
        if (this.props.redactor.input.isNewCourse) {
            if (this.props.redactor.input.courseTitle && this.props.redactor.input.courseTitle !== "" &&
                    this.props.redactor.input.courseType && this.props.redactor.input.taskTitle &&
                    this.props.redactor.input.taskTitle !== "" && this.props.redactor.input.content &&
                    this.props.redactor.input.content !== "") {
                this.props.postInitCourseWithTask({
                    course: {
                        title: this.props.redactor.input.courseTitle,
                        type: this.props.redactor.input.courseType,
                    },
                    content: this.props.redactor.input.content,
                    title: this.props.redactor.input.taskTitle,
                });
            } else alert("Empty course title or course type or task title or content");
        } else {
            if (this.props.redactor.input.chosenCourseId && this.props.redactor.input.taskTitle &&
                    this.props.redactor.input.taskTitle !== "" && this.props.redactor.input.content &&
                    this.props.redactor.input.content !== "") {
                this.props.postCourseTask({
                    courseId: this.props.redactor.input.chosenCourseId,
                    content: this.props.redactor.input.content,
                    title: this.props.redactor.input.taskTitle,
                });
            } else alert("Empty chosen course or task title or content");
        }

        event.preventDefault();
    }

    render() {
        if (this.props.redactor.answer != null && Number.isInteger(this.props.redactor.answer)) {
            const newsId = this.props.redactor.answer;
            this.setPostingNewsAnswer(newsId);
            return <Redirect to={"/task/" + this.props.redactor.answer}/>
        }

        const coursesData = this.props.redactor.userCourses;
        const inputCourseName = <div>
            <Row>
                <Col md={9}>
                    <FormControl name="course_title" type="text" placeholder="Course Title"
                                 onChange={this.handleChange} style={{marginBottom: "10px"}} value={this.props.redactor.input.courseTitle}/>
                </Col>
                <Col md={3}>
                    <FormControl componentClass="select" name="select_course_type" placeholder="Select Course Type"
                                 onChange={this.handleChange} style={{marginBottom: "15px"}}>
                        <option selected disabled>Choose course type</option>
                        {this.props.courseTypes == null ? "" : this.props.courseTypes.length === 0 ? "" :
                            this.props.courseTypes.map((type, i) =>
                                <option value={type} key={i}>{type}</option>)}
                        <option value="other">other</option>
                    </FormControl>
                </Col>
            </Row>
        </div>;

        return <Form onSubmit={this.handleSubmit} className="CourseTaskRedactorContainer">
            <PageHeader style={{textAlign: "center"}}>Create new Task for your Course!</PageHeader>
            {coursesData === null || coursesData.length === 0 ?
                <div>
                    <span style={{marginLeft: "10px"}}>Course Title</span>
                    {inputCourseName}
                </div> :
                <div>
                    <Checkbox name="select_is_new_course" onClick={this.handleChange}
                              defaultChecked={this.props.redactor.isNewCourse}>  {/*TODO: nav pills instead checkbox*/}
                        New Course?
                    </Checkbox>
                    <span style={{marginLeft: "10px"}}>Course Title</span>
                    {this.props.redactor.input.isNewCourse || coursesData === null || coursesData.length === 0 ?
                        inputCourseName :
                        <FormControl componentClass="select" name="select_course" placeholder="Select Course" onChange={this.handleChange} style={{marginBottom: "10px"}}>
                            <option selected disabled>Choose your Course!</option>
                            {coursesData.map((course, idx) => <option value={course.title} key={idx}>{course.title +
                            " (" + course.type +")"}</option>)}
                        </FormControl>}
                </div>}
            <span style={{marginLeft: "10px"}}>Task Title</span>
            <FormControl name="task_title" type="text" placeholder="Task Title" onChange={this.handleChange} style={{marginBottom: "10px"}}/>
            <FormControl componentClass="textarea" name="content" data-provide="markdown" rows="10"
                         onChange={this.handleChange}/>
            <hr/>
            <Button type="submit">Submit</Button>
        </Form>
    }
}

const mapStateToProps = (state) => {
    return {redactor: state.redactor}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(NewsRedactorContainer);