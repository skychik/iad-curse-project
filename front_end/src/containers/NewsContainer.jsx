import React from 'react';
import RestClient from "another-rest-client";
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import News from "../components/News";
import CommentsContainer from "./CommentsContainer";
import {Glyphicon, OverlayTrigger, Tooltip} from "react-bootstrap";
import {Link} from "react-router-dom";

// props:
//  news - News in JSON
class NewsContainer extends React.Component {
    // componentWillMount() {
    //     this.setNews(null);
    // }

    componentDidMount() {
        let api = new RestClient('http://localhost:8080'); // TODO: make static
        //console.log(this.props.match.params.number);
        const number = parseInt(this.props.match.params.number, 10);
        if (isNaN(number)) {
            // console.log("didMount:NaN");
            this.setNews(null);
            return;
        }
        // console.log("didMount:not NaN");

        const promise = api.res("news").res(number.toString()).get();
        promise.then((response) => {
            const news = JSON.parse(JSON.stringify(response));
            this.setNews(news);
        });
    }

    setNews(news) {
        // console.log("news: ");
        // console.log(news);
        return this.props.setNews(news);
    }

    render() {
        //console.log("NewsContainer.render");
        const newsData = this.props.news;
        // console.log('---data:---');
        // console.log(newsData);
        // console.log('---data---');

        if (newsData == null) {
            // console.log("render:null");
            return <h1>This news doesn't exist(incorrect news number)</h1>
        }
        if (Object.keys(newsData).length === 0) {
            return <h1>Loading</h1>
        }

        const loopTip = (<Tooltip id="tooltip_username">Put your Loop :)</Tooltip>);
        const poopTip = (<Tooltip id="tooltip_username">Put your Poop ;(</Tooltip>);
        const repostTip = (<Tooltip id="tooltip_username">Repost</Tooltip>);

        return (
            <div className="News_container">
                <News className="News"
                      newsId={newsData.id}
                      authorId={newsData.author.id}
                      authorUsername={newsData.author.username}
                      authorAvatar={newsData.author.photo}
                      title={newsData.title}
                      content={newsData.content}
                      creationDate={newsData.creationDate}
                      alteringDate={newsData.alteringDate}/>
                <div className="news_footer">
                    <span className="news_footer_comments">
                        Comments <span>{newsData.commentsNumber}</span>
                    </span>
                    <OverlayTrigger placement="top" overlay={poopTip}>
                        <Link className={"my_button"} style={{float: "right"}}>
                            <Glyphicon glyph="trash" />
                            <span>{newsData.poopsNumber}</span>
                        </Link>
                    </OverlayTrigger>
                    <OverlayTrigger placement="top" overlay={loopTip}>
                        <Link className={"my_button"} style={{float: "right"}}>
                            <Glyphicon glyph="repeat" />
                            <span>{newsData.loopsNumber}</span>
                        </Link>
                    </OverlayTrigger>
                    <OverlayTrigger placement="top" overlay={repostTip}>
                        <Link className={"my_button"} style={{float: "right"}}>
                            <Glyphicon glyph="retweet" />
                            <span>{newsData.repostsNumber}</span>
                        </Link>
                    </OverlayTrigger>
                </div>
                <CommentsContainer newsId={newsData.id}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        news: state.news,
    }
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(NewsContainer);