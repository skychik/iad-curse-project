import React from 'react';
import RestClient from "another-rest-client";
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import NewsPreview from "../components/NewsPreview";

// props:
//  news - News in JSON
class FeedContainer extends React.Component {
    // constructor(props) {
    //     super(props);
    //     this.setFeed = this.setFeed.bind(this);
    // }

    componentDidMount() {
        let api = new RestClient('http://localhost:8080'); // TODO: make static
        const promise = api.res("news").res("for").get({userId: 2});
        promise.then((response) => {
            const feed = JSON.parse(JSON.stringify(response));
            this.setFeed(feed);
        });
    }

    shouldComponentUpdate() {
        // console.log("shouldComponentUpdate()");
        // this.props.feed.map((news, idx) => {
        //     news.authorPhoto = news.author.id
        // });
        return true;
    }

    setFeed(feed) {
        // console.log("news: ");
        // console.log(news);
        return this.props.setFeed(feed); // after this this.props.content=data
    }

    render() {
        const data = this.props.feed;
        // console.log('---data:---');
        // console.log(data);
        // console.log('---data---');
        const feedContainer =  data != null ?
            data.map((newsPreview, idx) => {
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
                                    poopsNumber={newsPreview.poopsNumber}
                                    repostsNumber={newsPreview.repostsNumber}
                             />;
            }) : null;
        return(
            <div className="Feed_container">
                {feedContainer}
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