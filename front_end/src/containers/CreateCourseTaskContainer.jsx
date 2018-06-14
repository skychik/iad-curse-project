import * as actionCreators from "../actions";
import {connect} from "react-redux";
import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import {Button, Checkbox, Col, Form, FormControl, Glyphicon, PageHeader, Row} from "react-bootstrap";
import RestClient from "another-rest-client";
import cookie from "react-cookies";
import {bindActionCreators} from "redux";
import NewsRedactorContainer from "./NewsRedactorContainer";
import SoundcloudContainer from "./SoundcloudContainer";
import CourseTaskRedactorContainer from "./CourseTaskRedactorContainer";

class CreateCourseTaskContainer extends React.Component {
    componentWillMount() {
        this.props.setCourseBackground(true);
    }

    render() {
        return <Switch>
                <Route exact path='/create/course' component={() =>
                    <div>
                        <PageHeader style={{textAlign: "center"}}>
                            Create Course Task!
                        </PageHeader>
                        <Row style={{width: "90%", marginLeft: "5%"}}>
                            <Col md={6}>
                                <Button className="big-button" href="/create/course/redactor">
                                    <Glyphicon glyph="pencil" style={{color: "black", margin: "25px", fontSize: "73px"}} />
                                </Button>
                            </Col>
                            <Col md={6}>
                                <Button className="big-button" href="/create/course/soundcloud">
                                    <Glyphicon glyph="cloud" style={{color: "black", margin: "25px"}} />
                                </Button>
                            </Col>
                        </Row>
                    </div>} />
                <Route path='/create/course/redactor' component={CourseTaskRedactorContainer} />
                <Route path="/create/course/soundcloud" component={SoundcloudContainer}/>
            </Switch>
    }
}

const mapStateToProps = (state) => {
    return {redactor: state.redactor}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(CreateCourseTaskContainer);