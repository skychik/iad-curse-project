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
        const { authorId, authorUsername, creationDate, alteringDate, title, content,
            commentsNumber, loopsNumber, poopsNumber, repostsNumber } = this.props;

        if (this.id ==null) return <h1>This news doesn't exist(no such news number)</h1>;

        const markdown = <ReactMarkdown source={content} />;

        const tooltip = (
            <Tooltip id="tooltip_username">
                {authorUsername}
            </Tooltip>
        );

        return <div>
            <PageHeader bsClass={"my_page_header"}>
                <span style={{display: "inline-block", verticalAlign: "middle", overflow: "hidden", textOverflow: "ellipsis"}}>
                    {title}
                </span>

                <span style={{color: "transparent",float: "right"}}>
                {authorId === null ?
                    <Button href={"/id" + authorId} bsSize="small">
                        <OverlayTrigger placement="top" overlay={tooltip}>
                            <Glyphicon glyph="user"/>
                        </OverlayTrigger>
                    </Button> :
                    <OverlayTrigger placement="top" overlay={tooltip}>
                        <a href={"/id" + authorId}>
                            <Image src={"/photo/usr/icon" + authorId + ".jpg"} rounded/>
                        </a>
                    </OverlayTrigger>
                } {/* TODO: 32x32 */}
                </span>
            </PageHeader>
            <div id={"wrapper"}>{markdown}</div>
            <Panel.Footer>
                <div>
                    <a className={"my_button"}>
                        Comments
                        <span>{commentsNumber}</span>
                    </a>

                    <a className={"my_button"} style={{float: "right"}}>
                        Poops
                        <span>{poopsNumber}</span>
                    </a>
                    <a className={"my_button"} style={{float: "right"}}>
                        Loops
                        <span>{loopsNumber}</span>
                    </a>
                    <a className={"my_button"} style={{float: "right"}}>
                        Reposts
                        <span>{repostsNumber}</span>
                    </a>
                    {alteringDate == null ?
                        <span className={"my_date"} style={{float: "right"}}>
                            {News.getDateByString(creationDate).toLocaleDateString()}
                        </span> :
                        <span className={"my_date"} style={{float: "right"}}>
                            {News.getDateByString(alteringDate).toLocaleDateString()}
                        </span>}
                </div>
            </Panel.Footer>
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
    commentsNumber: PropTypes.number.isRequired,
    loopsNumber: PropTypes.number.isRequired,
    poopsNumber: PropTypes.number.isRequired,
    repostsNumber: PropTypes.number.isRequired,
};