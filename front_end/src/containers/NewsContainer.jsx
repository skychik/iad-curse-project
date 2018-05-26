import React from 'react';
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import News from "../components/News";
import CommentsContainer from "./CommentsContainer";
import PLoopButton from "../components/PLoopButton";

// props:
//  news - News in JSON
class NewsContainer extends React.Component {
    // componentWillMount() {
    //     this.setNews(null);
    // }

    componentDidMount() {
        this.setNews();
    }

    setNews() {
        const number = parseInt(this.props.match.params.number, 10);
        if (isNaN(number)) {
            this.props.setNews(null);
        } else {
            this.props.setNews(number.toString());
        }
    }

    render() {
        //console.log("NewsContainer.render");
        const newsData = this.props.news;
        console.log('---data:---');
        console.log(newsData);
        console.log('---data---');

        if (newsData == null) {
            // console.log("render:null");
            return <h1>This news doesn't exist(incorrect news number)</h1>
        }
        if (Object.keys(newsData).length === 0) {
            return <h1>Loading</h1>
        }

        const putLoopOnNewsId = () => this.props.putLoopOnNewsId(newsData.id);
        const putPoopOnNewsId = () => this.props.putPoopOnNewsId(newsData.id);
        const removeLoopOnNewsId = () => this.props.removeLoopOnNewsId(newsData.id);
        const removePoopOnNewsId = () => this.props.removePoopOnNewsId(newsData.id);

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
                    <PLoopButton isLoop={false} putAction={putPoopOnNewsId} removeAction={removePoopOnNewsId}
                                 counter={newsData.poopsNumber} tooltip={"Put your Poop ;("} float={"right"}
                                 wasPut={newsData.poopWasPut}/>
                    <PLoopButton isLoop={true} putAction={putLoopOnNewsId} removeAction={removeLoopOnNewsId}
                                 counter={newsData.loopsNumber} tooltip={"Put your Loop :)"} float={"right"}
                                 wasPut={newsData.loopWasPut}/>
                    {/*<OverlayTrigger placement="top" overlay={repostTip}>*/}
                        {/*<span className={"my_button"} style={{float: "right"}}>*/}
                            {/*<Glyphicon glyph="retweet" />*/}
                            {/*<span>{newsData.repostsNumber}</span>*/}
                        {/*</span>*/}
                    {/*</OverlayTrigger>*/}
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