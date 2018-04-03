// TODO: number of news reposts should have different icon from number of repost reposts.
// TODO: Number of news reposts contains number of it and repost reposts
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'
import Panel from "react-bootstrap/lib/Panel";
import {Badge, Button, Col, Glyphicon, Grid, Image, Label, Row} from "react-bootstrap";
import * as bootstrapUtils from "react-bootstrap/es/utils/bootstrapUtils";
import RestClient from "another-rest-client";

export default class News extends Component {
    constructor(props) {
        super(props);
        this.id = this.props.newsId;
    }

    componentDidMount() {
        // const restApi = new RestClient('http://localhost:8080');
        // this.authorId = this.props.authorId;
        // restApi.res({
        //     news: 0,
        // });
    }

    getLoopsNumber(newsId) {

    }

    getPoopsNumber(newsId) {

    }

    render() {
        const { newsId, authorId, creationDate, alteringDate, title, contentPreview } = this.props;
        const markdown = <ReactMarkdown source={contentPreview} />;

        bootstrapUtils.addStyle(Badge, 'date');
        bootstrapUtils.addStyle(Badge, 'altered-date');
        return <Panel>
            <Panel.Heading>
                    {/*<Row>**/}
                        {/*/!*<Col xs={1} sm={1} md={1} lg={1} >*!/*/}
                            <h3 style={{}}>
                                {title}
                                <div style={{float: "right", marginRight: 10}}>
                                    <Button href={"/id" + authorId} bsSize="small">
                                        <Glyphicon glyph="user"/>
                                    </Button>
                                </div>
                            </h3>
                        {/*/!*</Col>*!/*/}
                        {/*/!*<Col xs={1} sm={1} md={1} lg={1} >*!/*/}

                        {/*/!*</Col>*!/*/}
                    {/*/!*</Row>*!/*/}
            </Panel.Heading>
            <Panel.Body>
                {markdown}
            </Panel.Body>
            <Panel.Footer>
                <div style={{paddingBottom:18}}>
                    <Badge pullRight>{this.getPoopsNumber(newsId)}</Badge>
                    <div style={{width:10, float:"right"}} />  {/*TODO: распорки*/}
                    <Badge pullRight>{this.getLoopsNumber(newsId)}</Badge>
                    <div style={{width:10, float:"right"}} />
                    {alteringDate == null ?
                        <Badge pullRight>{creationDate}</Badge> :
                        <Badge pullRight>{alteringDate}</Badge>}
                </div>
            </Panel.Footer>
        </Panel>;
    }
}

News.propTypes = {
    newsId: PropTypes.number.isRequired,
    authorId: PropTypes.number.isRequired,
    title: PropTypes.string.isRequired,
    contentPreview: PropTypes.string.isRequired,
    creationDate: PropTypes.string.isRequired,
    alteringDate: PropTypes.string
};