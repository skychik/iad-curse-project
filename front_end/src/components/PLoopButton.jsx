import React, {Component} from "react";
import PropTypes from "prop-types";
import {Glyphicon, OverlayTrigger, Tooltip} from "react-bootstrap";


export default class PLoopButton extends Component {
    render() {
        const {isLoop, putAction, removeAction, counter, tooltip, wasPut, float} = this.props;
        const tip = (<Tooltip id="tooltip_username">{tooltip}</Tooltip>);
        let className;
        if (isLoop) {
            if (wasPut) {
                className =  "loop_button_active";
            } else {
                className =  "loop_button";
            }
        } else {
            if (wasPut) {
                className =  "poop_button_active";
            } else {
                className =  "poop_button"
            }
        }


        return <OverlayTrigger placement="top" overlay={tip} style={{float: float}}>
            <a onClick={wasPut ? removeAction : putAction} className={className} style={{float: float}}>
                {isLoop ? <Glyphicon glyph="repeat" /> : <Glyphicon glyph="trash" />}
                <span>{counter}</span>
            </a>
        </OverlayTrigger>
    }
}

PLoopButton.propTypes = {
    isLoop: PropTypes.bool.isRequired,
    putAction: PropTypes.func.isRequired,
    removeAction: PropTypes.func.isRequired,
    counter: PropTypes.number.isRequired,
    tooltip: PropTypes.string.isRequired,
    wasPut: PropTypes.bool.isRequired,
    float: PropTypes.string,
};
