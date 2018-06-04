import * as actionCreators from "../actions";
import {connect} from "react-redux";
import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import {Button, Col, Form, FormControl, Glyphicon, PageHeader, Row} from "react-bootstrap";
import RestClient from "another-rest-client";
import cookie from "react-cookies";
import {bindActionCreators} from "redux";

class SoundcloudContainer extends React.Component {
    render() {
        return <PageHeader style={{textAlign: "center"}}>Under Development...</PageHeader>
    }
}

const mapStateToProps = (state) => {
    return {redactor: state.redactor}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(SoundcloudContainer);