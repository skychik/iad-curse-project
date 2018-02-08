import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'

export default class News extends Component {
    render() {
        const { authorId, creationDate, alteringDate, content } = this.props;
        const markdown = <ReactMarkdown source={content} />;
        return <div className="News">
            <p>Author id={authorId}</p>
            <p>Creation date={creationDate}</p>
            <p>Altering date={alteringDate}</p>
            {markdown}
        </div>
    }
}

News.propTypes = {
    authorId: PropTypes.number.isRequired,
    creationDate: PropTypes.string.isRequired,
    content: PropTypes.string.isRequired
};