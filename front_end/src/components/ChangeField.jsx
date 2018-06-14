import React, { Component } from 'react'
import PropTypes from 'prop-types'
import UserPhoto from "./UserPhoto";
import DateTime from "./DateTime";
import {Button, Col, Form, FormControl, FormGroup, Modal, Row, Well} from "react-bootstrap";

export default class ChangeField extends Component {
    render() {
        const onClick = () => this.props.onClick(this.props.type, this.props.header, this.props.content);

        return <Row style={{margin: "5px"}}>
            <Col md={2} className="register-label">{this.props.header + ": "}</Col>
            <Col md={8}>{this.props.type === "sex" ?
                this.props.content == null ? "Attack Helicopter" : this.props.content ? "Male" : "Female"
                : this.props.type === "password" ? "******"
                : this.props.content}</Col>
            <Col md={1}><Button bsSize="xs" onClick={onClick}>change</Button></Col>
        </Row>
    }
}

ChangeField.propTypes = {
    header: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
    content: PropTypes.string,
    onClick: PropTypes.func.isRequired,
};