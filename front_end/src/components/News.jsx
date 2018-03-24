// TODO: number of news reposts should have different icon from number of repost reposts.
// TODO: Number of news reposts contains number of it and repost reposts
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'
import Panel from "react-bootstrap/lib/Panel";

export default class News extends Component {
    render() {
        const { authorId, creationDate, alteringDate, title, content } = this.props;
        const markdown = <ReactMarkdown source={content} />;
        return<Panel>
                <Panel.Heading>
                    <Panel.Title componentClass="h3">{title}</Panel.Title>
                </Panel.Heading>
                <Panel.Body>
                    <p>Author id={authorId}</p>
                    <p>Creation date={creationDate}</p>
                    <p>Altering date={alteringDate}</p>
                    {markdown}
                </Panel.Body>
            </Panel>;
    }
}

News.propTypes = {
    authorId: PropTypes.number.isRequired,
    creationDate: PropTypes.string.isRequired,
    alteringDate: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
    content: PropTypes.string.isRequired
};