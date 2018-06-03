import * as actionCreators from "../actions";
import {connect} from "react-redux";
import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import {Button, Col, Form, FormControl, Glyphicon, PageHeader, Row} from "react-bootstrap";
import RestClient from "another-rest-client";
import cookie from "react-cookies";
import {bindActionCreators} from "redux";

class CreateNewsContainer extends React.Component {
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
            this.setPostingNewsAnswer(newsId);
            return <Redirect to={"/news/" + this.props.newsMaker.answer}/>
        }

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
            <Route path='/create/news/redactor' component={() =>
                <Form onSubmit={this.handleSubmit}>
                    <FormControl name="title" type="text" placeholder="Title" onChange={this.handleChange}/>
                    <FormControl componentClass="textarea" name="content" data-provide="markdown" rows="10" onChange={this.handleChange}/>
                    <hr/>
                    <Button type="submit">Submit</Button>
                </Form>} />

            <Route path="/create/news/soundcloud" component={() =>
                <PageHeader style={{textAlign: "center"}}>Under Development...</PageHeader>}/>
        </Switch>
    }
}

const mapStateToProps = (state) => {
    return {newsMaker: state.newsMaker}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(CreateNewsContainer);