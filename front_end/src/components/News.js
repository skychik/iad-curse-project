import React, { Component } from 'react'
import PropTypes from 'prop-types'

export default class News extends Component {
    render() {
        const { authorId, creationDate, alteringDate } = this.props;
        return <div className="News_container">
            <p>Author id={authorId}</p>
            <p>Creation date={creationDate}</p>
            <p>Altering date={alteringDate}</p>
        </div>
    }
}

News.propTypes = {
    authorId: PropTypes.number.isRequired,
    creationDate: PropTypes.string.isRequired,
    alteringDate: PropTypes.string.isRequired
};