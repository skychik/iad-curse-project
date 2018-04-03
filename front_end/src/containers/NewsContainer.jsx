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
        const promise = api.res("news").get({userId: 2});
        promise.then((response) => {
            this.setFeed(JSON.parse(JSON.stringify(response)).content);
        });
    }

    setFeed(news) {
        // console.log("news: ");
        // console.log(news);
        return this.props.setFeed(news) // after this this.props.content=data
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
                             authorId={news.author.id}
                             title={news.title}
                             contentPreview={news.contentPreview}
                             creationDate={news.creationDate}
                             alteringDate={news.alteringDate}
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