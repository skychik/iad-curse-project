import React, { Component } from 'react'
import PropTypes from 'prop-types'

export default class DateTime extends Component {
    render() {
        const date = this.props.date;
        return <time dateTime={date.substr(0, 19)}>{/*without milliseconds and time zone*/}
            {"" + date.substr(8, 2) + "." + date.substr(5, 2) + "." + date.substr(0, 4) + " at " + date.substr(11, 5)}
        </time>
    }
}

DateTime.propTypes = {
    date: PropTypes.string.isRequired,
};