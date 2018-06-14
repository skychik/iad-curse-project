import React, {Component} from "react";
import PropTypes from "prop-types";
import {Button, Glyphicon, OverlayTrigger, Tooltip} from "react-bootstrap";


export default class ActionButton extends Component {
    render() {
        const {isLoop, isComment, action, alternativeAction, onAdd, counter, tooltip, wasPut, float, pending} = this.props;
        const tip = (<Tooltip id="tooltip_username">{tooltip}</Tooltip>);
        let className, glyph, onClick;
        if (isComment) {
            className =  "comment_button";
            glyph = "comment";
            onClick = action;
        } else if (isLoop) {
                if (wasPut) {
                    className =  "loop_button_active";
                    onClick = alternativeAction;
                } else {
                    className =  "loop_button";
                    onClick = action;
                }
                glyph = "repeat";
            } else {
                if (wasPut) {
                    className =  "poop_button_active";
                    onClick = alternativeAction;
                } else {
                    className =  "poop_button";
                    onClick = action;
                }
                glyph = "trash";
            }


        return <OverlayTrigger placement="top" overlay={tip} style={{float: float}}>
            <Button onClick={pending ? null : onClick} className={className} style={{float: float}}>
                <Glyphicon glyph={glyph} />
                <span>{counter}</span>
            </Button>
        </OverlayTrigger>
    }
}

ActionButton.propTypes = {
    isLoop: PropTypes.bool,
    isComment: PropTypes.bool,
    action: PropTypes.func.isRequired,
    alternativeAction: PropTypes.func,
    counter: PropTypes.number.isRequired,
    tooltip: PropTypes.string.isRequired,
    wasPut: PropTypes.bool,
    float: PropTypes.string,
    pending: PropTypes.bool.isRequired,
};
