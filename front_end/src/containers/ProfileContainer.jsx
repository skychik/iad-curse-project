import React  from 'react';
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import RestClient from "another-rest-client";
import {Button, Col, Glyphicon, Image, PageHeader, Row} from "react-bootstrap";
import NewsPreview from "../components/NewsPreview";
import {Link, Route, Switch} from "react-router-dom";
import CourseTaskPreview from "../components/CourseTaskPreview";

class ProfileContainer extends React.Component {
    componentDidMount() {
        console.log(parseInt(this.props.match.params.number, 10));
        this.props.setProfile(parseInt(this.props.match.params.number, 10));
    }

    setProfile(profile) {
        return this.props.setProfile(profile)
    }

    render() {
        const profileData = this.props.profile;

        if (profileData == null) {
            return <h1>This profile doesn't exist(incorrect id number)</h1>
        }
        if (Object.keys(profileData).length === 0) {
            return <h1>Loading...</h1>
        }

        const { id, username, firstName, surname, patronymic, birthDate, sex, photo,
            news, tasks, about } = profileData;
        const feedContainer =  news != null ?
            news.map((newsPreview, idx) => {
                const putLoopOnNewsId = () => this.props.putLoopOnTaskId(newsPreview.id);
                const putPoopOnNewsId = () => this.props.putPoopOnTaskId(newsPreview.id);
                const removeLoopOnNewsId = () => this.props.removeLoopOnTaskId(newsPreview.id);
                const removePoopOnNewsId = () => this.props.removePoopOnTaskId(newsPreview.id);

                return <NewsPreview className="NewsPreview"
                                    key={idx}
                                    newsId={newsPreview.id}
                                    authorId={newsPreview.author.id}
                                    authorUsername={newsPreview.author.username}
                                    authorAvatar={newsPreview.author.photo}
                                    title={newsPreview.title}
                                    contentPreview={newsPreview.contentPreview}
                                    creationDate={newsPreview.creationDate}
                                    alteringDate={newsPreview.alteringDate}
                                    commentsNumber={newsPreview.commentsNumber}
                                    loopsNumber={newsPreview.loopsNumber}
                                    loopWasPut={newsPreview.loopWasPut}
                                    poopsNumber={newsPreview.poopsNumber}
                                    poopWasPut={newsPreview.poopWasPut}
                                    repostsNumber={newsPreview.repostsNumber}
                                    putLoopOnNewsId={putLoopOnNewsId}
                                    putPoopOnNewsId={putPoopOnNewsId}
                                    removeLoopOnNewsId={removeLoopOnNewsId}
                                    removePoopOnNewsId={removePoopOnNewsId}/>;
            }) : "No news";
        console.log("feedContainer");
        console.log(feedContainer);

        const tasksContainer = tasks != null ?
            tasks.map((task, idx) => {
                const putLoopOnTaskId = () => this.props.putLoopOnTaskId(task.id);
                const putPoopOnTaskId = () => this.props.putPoopOnTaskId(task.id);
                const removeLoopOnTaskId = () => this.props.removeLoopOnTaskId(task.id);
                const removePoopOnTaskId = () => this.props.removePoopOnTaskId(task.id);

                return <CourseTaskPreview className="NewsPreview"
                                          key={idx}
                                          taskId={task.id}
                                          taskTitle={task.taskTitle}
                                          courseId={task.course.id}
                                          courseTitle={task.course.title}
                                          courseType={task.course.type}
                                          authorId={task.author.id}
                                          authorUsername={task.author.username}
                                          authorAvatar={task.author.photo}
                                          contentPreview={task.contentPreview}
                                          creationDate={task.creationDate}
                                          alteringDate={task.alteringDate}
                                          commentsNumber={task.commentsNumber}
                                          loopsNumber={task.loopsNumber}
                                          loopWasPut={task.loopWasPut}
                                          poopsNumber={task.poopsNumber}
                                          poopWasPut={task.poopWasPut}
                                          repostsNumber={task.repostsNumber}
                                          wasCompleted={task.wasCompleted}
                                          putLoopOnTaskId={putLoopOnTaskId}
                                          putPoopOnTaskId={putPoopOnTaskId}
                                          removeLoopOnTaskId={removeLoopOnTaskId}
                                          removePoopOnTaskId={removePoopOnTaskId}
                                          completeCourseTask={removePoopOnTaskId}
                                          undoCourseTask={removePoopOnTaskId} />;
            }) : "No tasks";

        return <Row className="profile">
            <Col md={3}>
                <div className="profile-sidebar">
                    <div className="profile-userpic">
                        <Image className="full_avatar" src={"/photo/usr/" + photo} alt="logo" responsive/>
                    </div>
                    <div className="profile-usertitle">
                        <div className="profile-usertitle-name">
                            <h3>@{username}</h3>
                            <h4>{firstName} {surname} {patronymic}</h4>
                        </div>
                    </div>
                    <div className="profile-userbuttons">
                        <Button className="btn btn-success btn-sm">Follow</Button>
                    </div>
                    <div className="profile-usermenu">
                        <ul className="nav">
                            <li className="active">
                                <Link to={"/id/" + id}>
                                    <Glyphicon glyph="home" /> News
                                </Link>
                            </li>
                            <li className="active">
                                <Link to={"/id/" + id + "/tasks"}>
                                    <Glyphicon glyph="home" /> Courses
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </Col>
            <Col md={9}>
                <div className="profile-content">
                    {/*/!*<div className="profile-about">*!/  TODO: "About" in profile of user*/}
                        {/*<div className="profile-about-title">About</div>*/}
                        {/*<div>{about}</div>*/}
                    {/*</div>*/}
                    <Switch>
                        <Route path='/id/:number/tasks' component={
                            () => <div>
                                <PageHeader>Course Tasks</PageHeader>
                                {tasksContainer.length === 0 ?
                                    <span className={"profile-no-tasks"}>No tasks</span>
                                    : tasksContainer}
                            </div>} />
                        <Route component={
                            () => <div>
                                <PageHeader>News</PageHeader>
                                {feedContainer.length === 0 ?
                                    <span className={"profile-no-news"}>No news</span>
                                    : feedContainer}
                            </div>} />
                    </Switch>
                </div>
            </Col>
        </Row>
    }
}

const mapStateToProps = (state) => {
    return {profile: state.profile}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(ProfileContainer);