import React, { Component } from 'react'
import PropTypes from 'prop-types'
import {Button, Glyphicon, Image, OverlayTrigger, Tooltip} from "react-bootstrap";
import {Link} from "react-router-dom";

export default class UserPhoto extends Component {
    render() {
        const tooltip = (
            <Tooltip id="tooltip_username">
                @{this.props.username}
            </Tooltip>
        );

        if (this.props.withTooltip) {
            return this.props.userId === null ?
                <Button href={"/id" + this.props.userId} bsSize="small">
                    <OverlayTrigger placement="top" overlay={tooltip}>
                        <Glyphicon glyph="user"/>
                    </OverlayTrigger>
                </Button>
                :
                <OverlayTrigger placement="top" overlay={tooltip}>
                    <Link href={"/id" + this.props.userId}>
                        <Image src={"/photo/usr/" + this.props.photo} />
                    </Link>
                </OverlayTrigger>
        } else {
            return <Link href={"/id" + this.props.userId}>
                <span className="img_container">
                    <img src={"/photo/usr/" + this.props.photo} alt="logo"/>
                </span>
                <span className="username">@{this.props.username}</span>
            </Link>
        }
    }
}

UserPhoto.propTypes = {
    withTooltip: PropTypes.bool.isRequired,
    userId: PropTypes.number.isRequired,
    username: PropTypes.string.isRequired,
    photo: PropTypes.string.isRequired,
};