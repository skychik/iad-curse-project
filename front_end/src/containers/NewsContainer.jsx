import React from 'react';
import RestClient from "another-rest-client";
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import News from "../components/News";

// props:
//  news - News in JSON
class NewsContainer extends React.Component {
    constructor(props) {
        super(props);
        this.setFeed = this.setFeed.bind(this);
    }

    componentDidMount() {
        let api = new RestClient('http://localhost:8080'); // TODO: make static
        const promise = api.res("news").res("for").get({userId: 2});
        promise.then((response) => {
            const feed = JSON.parse(JSON.stringify(response));
            this.setFeed(feed);
        });
    }

    shouldComponentUpdate() {
        console.log("shouldComponentUpdate()");
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
        console.log('---data:---');
        console.log(data);
        console.log('---data---');
        const newsContainer =  data != null ?
            data.map((news, idx) => {
                return <News className="News"
                             key={idx}
                             newsId={news.id}
                             authorId={news.authorId}
                             authorUsername={news.authorUsername}
                             title={news.title}
                             contentPreview={news.contentPreview}
                             creationDate={news.creationDate}
                             alteringDate={news.alteringDate}
                             commentsNumber={news.commentsNumber}
                             loopsNumber={news.loopsNumber}
                             poopsNumber={news.poopsNumber}
                             repostsNumber={news.repostsNumber}
                             />;
            }) : null;
        return(
            <div className="News_container">
                {newsContainer}
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


export default connect(mapStateToProps, mapDispatchToProps)(NewsContainer);