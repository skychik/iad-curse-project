import React, {Component} from "react";
import PropTypes from "prop-types";
import {Glyphicon, OverlayTrigger, Tooltip} from "react-bootstrap";


export default class PLoopButton extends Component {
    render() {
        const tip = (<Tooltip id="tooltip_username">{this.props.tooltip}</Tooltip>);

        return <OverlayTrigger placement="top" overlay={tip} style={{float: this.props.float}}>
            <a onClick={this.props.action}
               className={this.props.isLoop ? "my_button loop_button" : "my_button poop_button"} style={{float: this.props.float}}>
                {this.props.isLoop ? <Glyphicon glyph="repeat" /> : <Glyphicon glyph="trash" />}
                <span>{this.props.counter}</span>
            </a>
        </OverlayTrigger>
    }
}

PLoopButton.propTypes = {
    isLoop: PropTypes.bool.isRequired,
    action: PropTypes.func.isRequired,
    counter: PropTypes.bool.isRequired,
    tooltip: PropTypes.string.isRequired,
    float: PropTypes.string,
};
