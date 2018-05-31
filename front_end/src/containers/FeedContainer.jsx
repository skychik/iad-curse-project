import React from 'react';
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import NewsPreview from "../components/NewsPreview";
import {Glyphicon, OverlayTrigger, PageHeader, Tooltip} from "react-bootstrap";

// props:
//  news - News in JSON
class FeedContainer extends React.Component {
    componentDidMount() {
        this.props.setFeed();
    }

    shouldComponentUpdate() {
        // console.log("shouldComponentUpdate()");
        // this.props.feed.map((news, idx) => {
        //     news.authorPhoto = news.author.id
        // });
        return true;
    }

    render() {
        const data = this.props.feed;
        // console.log('---data:---');
        // console.log(data);
        // console.log('---data---');
        const feedContainer =  data != null ?
            data.map((newsPreview, idx) => {
                const putLoopOnNewsId = () => this.props.putLoopOnTaskId(newsPreview.id);
                const putPoopOnNewsId = () => this.props.putPoopOnTaskId(newsPreview.id);
                const removeLoopOnNewsId = () => this.props.removeLoopOnTaskId(newsPreview.id);
                const removePoopOnNewsId = () => this.props.removePoopOnTaskId(newsPreview.id);

                return <NewsPreview className="NewsPreview"
                                    key={idx}
                                    newsId={newsPreview.id}
                                    authorId={newsPreview.author.id}
                                    authorUsername={newsPreview.author.username}
                                    authorAvatar={newsPreview.author.photo}
                                    title={newsPreview.title}
                                    contentPreview={newsPreview.contentPreview}
                                    creationDate={newsPreview.creationDate}
                                    alteringDate={newsPreview.alteringDate}
                                    commentsNumber={newsPreview.commentsNumber}
                                    loopsNumber={newsPreview.loopsNumber}
                                    loopWasPut={newsPreview.loopWasPut}
                                    poopsNumber={newsPreview.poopsNumber}
                                    poopWasPut={newsPreview.poopWasPut}
                                    putLoopOnNewsId={putLoopOnNewsId}
                                    putPoopOnNewsId={putPoopOnNewsId}
                                    removeLoopOnNewsId={removeLoopOnNewsId}
                                    removePoopOnNewsId={removePoopOnNewsId}
                             />;
            }) : [];

        return(
            <div className="Feed_container">
                <PageHeader style={{textAlign: "center"}}>
                    Feed
                </PageHeader>
                <div>{feedContainer.length === 0 ? <span className={"feed-no-news"}>No news</span> : feedContainer}</div>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {feed: state.feed}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(FeedContainer);