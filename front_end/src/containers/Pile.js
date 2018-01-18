import React from 'react';
import RestClient from "another-rest-client";
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";

// props:
//  news - News in JSON
class Pile extends React.Component {
    constructor(props) {
        super(props);
        this.receiveNews = this.receiveNews.bind(this);
    }

    componentDidMount() {
        const restApi = new RestClient('http://localhost:8080');
        restApi.res({
            api:
                'news'
        });
        // console.log(restApi.api.news.url());
        const smth = restApi.api.news.get();
        // smth.then((response) => {
        //     console.log(response);
        // });

        smth.then((response) => {
            const array = JSON.parse(response)._embedded.news;
            // console.log(array);
            const data = array.map((news, idx) => {
                return <li key={idx}>
                        Author id={news.authorId},
                        Creation date={news.creationDate},
                        Altering date={news.alteringDate}
                    </li>;
            });
            this.receiveNews(data);
        });
    }

    receiveNews(data) {
        return this.props.receiveNews(data)
    }

    render() {
        const news = this.props.content;
        // console.log('---news:---');
        // console.log(news);
        // console.log('---news---');

        return(
            <div className="Pile">
                {news}
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


export default connect(mapStateToProps, mapDispatchToProps)(Pile);