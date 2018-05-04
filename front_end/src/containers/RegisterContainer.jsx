import React from "react";
import {
    Button,
    Checkbox,
    Col,
    ControlLabel,
    Form,
    FormControl,
    FormGroup, Glyphicon,
    Grid,
    InputGroup, Label,
    Panel, Radio, Well
} from "react-bootstrap";
import {Link} from "react-router-dom";

class RegisterContainer extends React.Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        alert('A name was submitted: ' + this.state.value);
        event.preventDefault();
    }

    render() {
        return <Grid className="RegisterContainer">
            <div className="register-inner">
                <h1>Muzloop</h1>
                <Panel className="register-form-container">
                    <h4 className="register-title">Register</h4>
                    <Form horizontal>
                        <FormGroup controlId="formName">
                            <p className="register-label">Name</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="user"/></InputGroup.Addon>
                                    <FormControl type="text" name="name" id="name" placeholder="Enter your Login" onChange={this.handleChange} />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formSex">
                            <p className="register-label">Sex</p>
                            <Col sm={12} className="register-radios" >
                                <div className="register-radio-container">
                                    <Radio name="radioGroup" inline>Male</Radio>{' '}
                                    <Radio name="radioGroup" inline>Female</Radio>{' '}
                                    <Radio name="radioGroup" inline>Attack Helicopter</Radio>
                                </div>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formEmail">
                            <p className="register-label">Email</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="envelope"/></InputGroup.Addon>
                                    <FormControl type="text" name="email" id="email" placeholder="Enter your Email" onChange={this.handleChange} />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formLogin">
                            <p className="register-label">Login</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="user"/></InputGroup.Addon>
                                    <FormControl type="login" name="login" id="login" placeholder="Enter your Login" onChange={this.handleChange} />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formPassword">
                            <p className="register-label">Password</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="lock"/></InputGroup.Addon>
                                    <FormControl type="password" name="password" id="password" placeholder="Enter your Password" onChange={this.handleChange} />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formConfirm">
                            <p className="register-label">Confirm Password</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="lock"/></InputGroup.Addon>
                                    <FormControl type="text" name="confirm" id="confirm"
                                                 placeholder="Confirm your Password" onChange={this.handleChange} />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup>
                            <Col sm={12} className="register-submit-container">
                                <Button type="submit" block>Register</Button>
                            </Col>
                        </FormGroup>

                        Already have an account? <Link to="/login">Sign in</Link>!
                    </Form>
                </Panel>
            </div>
        </Grid>;
    }
}

export default RegisterContainer;