import React, { Component } from 'react'
import PropTypes from 'prop-types'
import UserPhoto from "./UserPhoto";
import DateTime from "./DateTime";
import {Button, Form, FormControl, FormGroup, Modal, Well} from "react-bootstrap";

export default class CommentModal extends Component {
    render() {

        return <Modal show={this.props.isShown} onHide={this.props.onHide}
                      bsSize="large" aria-labelledby="contained-modal-title-lg">
            <Form onSubmit={this.handleSubmitNewComment}>
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-sm">Add comment</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormGroup controlId="formControlsTextarea">
                        {(this.props.comment != null) ? (this.props.comment.content !== null) ?
                            <div>
                                <UserPhoto userId={this.props.comment.userId} username={this.props.comment.username}
                                           withTooltip={false} photo={this.props.comment.avatar} />
                                <DateTime date={this.props.comment.creationDate} />
                                <Well style={{overflow: "hidden", textOverflow: "ellipsis"}}>
                                    {this.props.comment.content}
                                </Well>
                            </div> : null : null}
                        <FormControl componentClass="textarea" placeholder="Write your comment..." name="content"
                                     style={{resize: "none"}} rows="10" onChange={this.props.onChange} autoFocus>
                            {this.props.content}
                        </FormControl>
                    </FormGroup>
                </Modal.Body>
                <Modal.Footer>
                    <Button type="submit" onClick={this.props.onSubmit}>Send</Button>
                    <Button onClick={this.props.onHide}>Close</Button>
                </Modal.Footer>
            </Form>
        </Modal>
    }
}

CommentModal.propTypes = {
    comment: PropTypes.object,
    content: PropTypes.string.isRequired,
    isShown: PropTypes.bool.isRequired,
    onHide: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    onSubmit: PropTypes.func.isRequired,
};