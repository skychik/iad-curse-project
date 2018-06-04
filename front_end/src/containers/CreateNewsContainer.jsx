import * as actionCreators from "../actions";
import {connect} from "react-redux";
import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import {Button, Col, Form, FormControl, Glyphicon, PageHeader, Row} from "react-bootstrap";
import RestClient from "another-rest-client";
import cookie from "react-cookies";
import {bindActionCreators} from "redux";
import SoundcloudContainer from "./SoundcloudContainer";
import NewsRedactorContainer from "./NewsRedactorContainer";

class CreateNewsContainer extends React.Component {
    render() {
        return <Switch>
            <Route exact path='/create/news' component={() =>
                <div>
                    <PageHeader style={{textAlign: "center"}}>
                        Create News!
                    </PageHeader>
                    <Row style={{width: "90%", marginLeft: "5%"}}>
                        <Col md={6}>
                            <Button className="big-button" href="/create/news/redactor">
                                <Glyphicon glyph="pencil" style={{color: "black", margin: "25px", fontSize: "73px"}} />
                            </Button>
                        </Col>
                        <Col md={6}>
                            <Button className="big-button" href="/create/news/soundcloud">
                                <Glyphicon glyph="cloud" style={{color: "black", margin: "25px"}} />
                            </Button>
                        </Col>
                    </Row>
                </div>} />
            <Route path='/create/news/redactor' component={NewsRedactorContainer} />
            <Route path="/create/news/soundcloud" component={SoundcloudContainer}/>
        </Switch>
    }
}

const mapStateToProps = (state) => {
    return {redactor: state.redactor}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(CreateNewsContainer);