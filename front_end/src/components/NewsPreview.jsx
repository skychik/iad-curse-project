// TODO: number of news reposts should have different icon from number of repost reposts.
// TODO: Number of news reposts contains number of it and repost reposts
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'
import Panel from "react-bootstrap/lib/Panel";
import {Row} from "react-bootstrap";
import UserPhoto from "./UserPhoto";
import DateTime from "./DateTime";
import {Link} from "react-router-dom";
import PLoopButton from "./PLoopButton";

export default class NewsPreview extends Component {
    componentDidMount() {
        // const restApi = new RestClient('http://localhost:8080');
        // this.authorId = this.props.authorId;
        // restApi.res({
        //     news: 0,
        // });
    }

    // static getDateByString(str) {
    //     return new Date(parseInt(str.substring(0, 4)), parseInt(str.substring(5, 7)), parseInt(str.substring(8, 10)),
    //         parseInt(str.substring(11, 13)), parseInt(str.substring(14, 16)), parseInt(str.substring(17, 19)),
    //         parseInt(str.substring(20, 22)));
    // }

    render() {
        const { newsId, authorId, authorUsername, creationDate, alteringDate, title, contentPreview,
            commentsNumber, loopsNumber, poopsNumber, authorAvatar } = this.props;
        const markdown = <ReactMarkdown source={contentPreview} />;

        return <Panel>
            <Panel.Heading>{/* style={{backgroundColor: "rgba(238, 238, 238, 0.5)"}}*/}
                    <Row>
                        <div style={{display: "block", width: "74px", float: "right"}}>
                            <UserPhoto userId={authorId} username={authorUsername} withTooltip={true}
                                       photo={authorAvatar} size={64}/>
                            {/*<div style={{color: "transparent",float: "right", marginRight: 10}}>*/}
                            {/*{authorId === null ?*/}
                            {/*<Button href={"/id" + authorId} bsSize="small">*/}
                            {/*<OverlayTrigger placement="top" overlay={tooltip}>*/}
                            {/*<Glyphicon glyph="user"/>*/}
                            {/*</OverlayTrigger>*/}
                            {/*</Button> :*/}
                            {/*<OverlayTrigger placement="top" overlay={tooltip}>*/}
                            {/*<a href={"/id" + authorId}>*/}
                            {/*<Image src={"/photo/usr/icon" + authorId + ".jpg"} rounded/>*/}
                            {/*</a>*/}
                            {/*</OverlayTrigger>*/}
                            {/*} /!* TODO: 32x32 *!/*/}
                            {/*</div>*/}
                        </div>
                        <div style={{display: "block", marginRight: "94px"}}>
                            <h3 className="news_preview_title">
                                <Link to={"/news/" + newsId}>{title}</Link>
                            </h3>
                        </div>
                    </Row>
            </Panel.Heading>
            <Panel.Body style={{paddingTop: "0", paddingBottom: "0"}}>
                <div id={"wrapper"}>{markdown}</div>
            </Panel.Body>
            <Panel.Footer>
                <div>
                    <span className={"my_button"}>
                        Comments
                        <span>{commentsNumber}</span>
                    </span>

                    <PLoopButton isLoop={false} putAction={this.props.putPoopOnNewsId}
                                 removeAction={this.props.removePoopOnNewsId} counter={poopsNumber}
                                 tooltip={"Put your Poop ;("} float={"right"} wasPut={this.props.poopWasPut}/>
                    <PLoopButton isLoop={true} putAction={this.props.putLoopOnNewsId}
                                 removeAction={this.props.removeLoopOnNewsId} counter={loopsNumber}
                                 tooltip={"Put your Loop :)"} float={"right"} wasPut={this.props.loopWasPut}/>
                    {/*<span className={"my_button"} style={{float: "right"}}>*/}
                        {/*Reposts*/}
                        {/*<span>{repostsNumber}</span>*/}
                    {/*</span>*/}
                    <span className={"my_date"} style={{float: "right"}}>
                        <span style={{marginRight: 10}}>
                            <DateTime date={alteringDate == null ? creationDate : alteringDate} />
                        </span>
                    </span>
                </div>
            </Panel.Footer>
        </Panel>;
    }
}

NewsPreview.propTypes = {
    newsId: PropTypes.number.isRequired,
    authorId: PropTypes.number.isRequired,
    authorUsername: PropTypes.string.isRequired,
    authorAvatar: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
    contentPreview: PropTypes.string.isRequired,
    creationDate: PropTypes.string.isRequired,
    alteringDate: PropTypes.string,
    commentsNumber: PropTypes.number.isRequired,
    loopsNumber: PropTypes.number.isRequired,
    loopWasPut: PropTypes.bool.isRequired,
    poopsNumber: PropTypes.number.isRequired,
    poopWasPut: PropTypes.bool.isRequired,
    putLoopOnNewsId: PropTypes.func.isRequired,
    putPoopOnNewsId: PropTypes.func.isRequired,
    removeLoopOnNewsId: PropTypes.func.isRequired,
    removePoopOnNewsId: PropTypes.func.isRequired,
    repostsNumber: PropTypes.number,
};