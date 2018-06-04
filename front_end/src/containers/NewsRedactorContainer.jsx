import * as actionCreators from "../actions";
import {connect} from "react-redux";
import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import {Button, Col, Form, FormControl, Glyphicon, PageHeader, Row} from "react-bootstrap";
import RestClient from "another-rest-client";
import cookie from "react-cookies";
import {bindActionCreators} from "redux";

class NewsRedactorContainer extends React.Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    setPostingNewsAnswer(response) {
        this.props.setPostingNewsAnswer(response);
    }

    handleChange(event) {
        switch (event.target.name) {
            case "title":
                console.log(event.target.value);
                this.props.setRedactorNewsTitle(event.target.value); break;
            case "content":
                this.props.setRedactorContent(event.target.value); break;
        }
    }

    handleSubmit(event) {
        if (this.props.redactor.input.content && this.props.redactor.input.content !== "" && this.props.redactor.input.newsTitle &&
                this.props.redactor.input.newsTitle !== "") {
            this.props.postNews({
                content: this.props.redactor.input.content,
                title: this.props.redactor.input.newsTitle,
            });
        } else alert("Empty title or content");

        event.preventDefault();
    }

    render() {
        console.log("answer=" + this.props.redactor.answer);
        if (!isNaN(this.props.redactor.answer) && this.props.redactor.answer !== null) {
            console.log("redirect to=news/" + this.props.redactor.answer);
            return <Redirect to={"/news/" + this.props.redactor.answer}/>
        } else {
            return <Form onSubmit={this.handleSubmit} className="NewsRedactorContainer">
                <FormControl name="title" type="text" placeholder="Title" onChange={this.handleChange}/>
                <FormControl componentClass="textarea" name="content" data-provide="markdown" rows="10"
                             onChange={this.handleChange}/>
                <hr/>
                <Button type="submit">Submit</Button>
            </Form>
        }
    }
}

const mapStateToProps = (state) => {
    return {redactor: state.redactor}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(NewsRedactorContainer);