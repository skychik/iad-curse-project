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
        this.receiveNews = this.receiveNews.bind(this);
    }

    componentDidMount() {
        const restApi = new RestClient('http://localhost:8080');
        restApi.res({
            api:
                'news',
            all_news: 0
        });
        // console.log(restApi.api.news.url());
        const smth = restApi.api.news.get();
        // smth.then((response) => {
        //     console.log(response);
        // });

        smth.then((response) => {
            console.log(response);
            const data = JSON.parse(response)._embedded.news;
            this.receiveNews(data);
            // this.receiveNews(response);
        });
    }

    receiveNews(data) {
        return this.props.receiveNews(data)
    }

    render() {
        const data = this.props.content;
        // console.log('---news:---');
        // console.log(news);
        // console.log('---news---');
        const newsContainer = data != null ?
            data.map((news, idx) => {
                return <News className="News" key={idx}
                             authorId={news.authorId}
                             creationDate={news.creationDate}
                             alteringDate={news.alteringDate}
                             content={news.content}/>;
            }) : null;
        return(
            <div className="News_container">
                {newsContainer}
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {content: state.content}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(NewsContainer);