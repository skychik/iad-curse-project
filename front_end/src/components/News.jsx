// TODO: number of news reposts should have different icon from number of repost reposts.
// TODO: Number of news reposts contains number of it and repost reposts
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'
import Panel from "react-bootstrap/lib/Panel";
import {
    Badge,
    Button,
    Col,
    Glyphicon,
    Grid,
    Image,
    Label,
    OverlayTrigger, PageHeader,
    Row,
    Thumbnail,
    Tooltip
} from "react-bootstrap";
import * as bootstrapUtils from "react-bootstrap/es/utils/bootstrapUtils";
import RestClient from "another-rest-client";
import Comment from "./Comment";

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

    static getDateByString(str) {
        return new Date(parseInt(str.substring(0, 4)), parseInt(str.substring(5, 7)), parseInt(str.substring(8, 10)),
            parseInt(str.substring(11, 13)), parseInt(str.substring(14, 16)), parseInt(str.substring(17, 19)),
            parseInt(str.substring(20, 22)));
    }

    render() {
        const { authorId, authorUsername, creationDate, alteringDate, title, content } = this.props;

        if (this.id ==null) return <h1>This news doesn't exist(no such news number)</h1>;

        const markdown = <ReactMarkdown source={content} />;

        return <div>
            <div className="page_header_info">
                <a href={"/id" + authorId}>
                    <span className="page_header_info_img_container">
                        <img src={"/photo/usr/icon" + authorId + ".jpg"}
                             onError="this.onerror=null; this.src=/photo/usr/default.png" />
                    </span>
                    <span className="page_header_info_username">{authorUsername}</span>
                </a>
                {alteringDate == null ?
                    <time dateTime={creationDate.substr(0, 19)}>  {/*without milliseconds and time zone*/}
                        {Comment.getPrettyTimeDateString(creationDate)}
                    </time> :
                    <time dateTime={alteringDate.substr(0, 19)}>  {/*without milliseconds and time zone*/}
                        {Comment.getPrettyTimeDateString(alteringDate)}
                    </time>}
            </div>
            <PageHeader bsClass="page_header">
                    {title}
            </PageHeader>
            <div id={"wrapper"}>{markdown}</div>
        </div>;
    }
}

News.propTypes = {
    newsId: PropTypes.number.isRequired,
    authorId: PropTypes.number.isRequired,
    authorUsername: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
    content: PropTypes.string.isRequired,
    creationDate: PropTypes.string.isRequired,
    alteringDate: PropTypes.string,
};