import React from 'react';
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import {Button, Checkbox, Form, FormControl} from "react-bootstrap";
import cookie from "react-cookies";
import {Redirect} from "react-router-dom";
import RestClient from "another-rest-client";

class MakeNewsContainer extends React.Component {
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
                this.props.setNewsMakerTitle(event.target.value); break;
            case "content":
                this.props.setNewsMakerContent(event.target.value); break;
        }
    }

    handleSubmit(event) {
        if (this.props.newsMaker.content && this.props.newsMaker.title) {
            let api = new RestClient('http://localhost:8080');
            console.log("posting news");
            const promise = api.res("news").res("post").post({
                authorId: cookie.load("userId"),
                content: this.props.newsMaker.content,
                title: this.props.newsMaker.title,
            });
            console.log("...");
            promise.then((response) => {
                console.log("posting news answer=" + response);
                this.setPostingNewsAnswer(response);
            });
            //
            // this.props.history.push("/login");
        } else alert("Empty title or content");

        event.preventDefault();
    }

    render() {
        if (this.props.newsMaker.answer != null && this.props.newsMaker.answer.isNumber()) {
            const newsId = this.props.newsMaker.answer;
            this.setPostingNewsAnswer(null);
            return <Redirect to={"/news/" + this.props.newsMaker.answer}/>
        }

        return <Form onSubmit={this.handleSubmit}>
            <FormControl name="title" type="text" placeholder="Title" onChange={this.handleChange}/>
            <FormControl componentClass="textarea" name="content" data-provide="markdown" rows="10" onChange={this.handleChange}/>
            <Checkbox name="publish">Publish</Checkbox>
            <hr/>
            <Button type="submit">Submit</Button>
        </Form>
    }
}

const mapStateToProps = (state) => {
    return {newsMaker: state.newsMaker}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(MakeNewsContainer);