import React from "react";
import {
    Button,
    Col,
    Form,
    FormControl,
    FormGroup, Glyphicon,
    Grid, InputGroup,
    Panel, Radio
} from "react-bootstrap";
import {Link} from "react-router-dom";
import validator from "validator";
import {debounce} from "lodash";
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

class RegisterContainer extends React.Component {
    constructor(props) {
        super(props);

        // this.state = {
        //     firstName: null,
        //     surname: null,
        //     sex: null,
        //     email: null,
        //     login: null,
        //     password: null,
        //     confirmation: null,
        //     validation: {
        //         firstName: null,
        //         surname: null,
        //         sex: null,
        //         email: null,
        //         login: null,
        //         password: null,
        //         confirmation: null,
        //     },
        //     validationStarted: {
        //         firstName: false,
        //         surname: false,
        //         sex: false,
        //         email: false,
        //         login: false,
        //         password: false,
        //         confirmation: false,
        //     },
        // };


        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentWillMount() {
        this.initValidation();
    }

    initValidation() {
        return this.props.initValidation();
    }

    startValidation(name) {
        //console.log("startValidation");
        // switch (type) {
        //     case "firstName":
        //         this.state.validationStarted.firstName = true; break;
        //     case "surname":
        //         this.state.validationStarted.surname = true; break;
        //     case "sex":
        //         this.state.validationStarted.sex = true; break;
        //     case "email":
        //         this.state.validationStarted.email = true; break;
        //     case "login":
        //         this.state.validationStarted.login = true; break;
        //     case "password":
        //         this.state.validationStarted.password = true; break;
        //     case "confirmation":
        //         this.state.validationStarted.confirmation = true; break;
        // }
        return this.props.startValidation(name);
    }

    setValidation(obj) {
        return this.props.setValidation(obj);
    }

    validate(target, isValidationStarted) {
        if (isValidationStarted) {
            this.setValidation({
                name: target.name,
                value: target.value,
            });
        } else {
            debounce(() => {
                this.startValidation(target.name);
                this.validate(target, true);
            }, 1000)();
        }
    }

    handleChange(event) {
        switch (event.target.name) {
            case "firstName":
                this.validate(event.target, this.props.registration.validationStarted.firstName); break;
            case "surname":
                this.validate(event.target, this.props.registration.validationStarted.surname); break;
            case "email":
                this.validate(event.target, this.props.registration.validationStarted.email); break;
            case "login":
                this.validate(event.target, this.props.registration.validationStarted.login); break;
            case "password":
                this.validate(event.target, this.props.registration.validationStarted.password); break;
            case "confirmation":
                this.validate(event.target, this.props.registration.validationStarted.confirmation); break;
        }


        // event.persist(); // to use debounce
        // const validationStarted = this.state.validationStarted;

        // switch (event.target.name) {
        //     case "firstName":
        //         this.state.firstName = event.target.value;
        //         if (!validationStarted.firstName) {
        //             //console.log("debounce");
        //             debounce((() => {
        //                 this.startValidation("firstName");
        //                 this.handleChange(event);
        //             }), 1000)();
        //         } else {
        //             //console.log("validation");
        //
        //             // this.state.validation.firstName = validator.isAlphanumeric(event.target.value) ? "success" : "error";
        //             this.setValidation(validator.isAlphanumeric(event.target.value) ? "success" : "error");
        //             console.log(this.state.validation.firstName);
        //         }
        //         break;
        //     case "surname":
        //         this.state.surname = event.target.value;
        //         if (!validationStarted.surname) {
        //             debounce((() => {
        //                 this.startValidation("surname");
        //                 this.handleChange(event);
        //             }), 1000)();
        //         } else {
        //             //this.state.validation.surname = validator.isAlphanumeric(event.target.value) ? "success" : "error";
        //             this.setValidation(validator.isAlphanumeric(event.target.value) ? "success" : "error");
        //         }
        //         break;
        //     // case "sex":
        //     //     this.state.sex = event.target.value;
        //     //     if (!validationStarted.sex) {
        //     //         debounce((() => {
        //     //             this.startValidation("sex");
        //     //             this.handleChange(event);
        //     //         }), 1000)();
        //     //     } else {
        //     //         this.state.validation.sex =
        //     //             validator.isAlphanumeric(event.target.value) ? "success" : "error";
        //     //     }
        //     //     break;
        //     case "email":
        //         this.state.email = event.target.value; break;
        //     case "login":
        //         this.state.login = event.target.value; break;
        //     case "password":
        //         this.state.password = event.target.value; break;
        //     case "confirmation":
        //         this.state.confirmation = event.target.value; break;
        // }
        // console.log("END " + event.target.name + " " + event.target.value);
    }

    canSubmit() {
        console.log(this.props.registration.validation.firstName && this.props.registration.validation.surname &&
            this.props.registration.validation.email && this.props.registration.validation.login &&
            this.props.registration.validation.password && this.props.registration.validation.confirmation);
        return this.props.registration.validation.firstName && this.props.registration.validation.surname &&
            this.props.registration.validation.email && this.props.registration.validation.login &&
            this.props.registration.validation.password && this.props.registration.validation.confirmation;
    }

    handleSubmit(event) {
        console.log("register");

        event.preventDefault();
    }

    render() {
        return <Grid className="RegisterContainer">
            <div className="register-inner">
                <h1>Muzloop</h1>
                <Panel className="register-form-container">
                    <h4 className="register-title">Register</h4>
                    <Form horizontal onSubmit={this.handleSubmit}>
                        <FormGroup controlId="formFirstName" validationState={
                            this.props.registration.validation.firstName == null ? null : this.props.registration.validation.firstName}>
                            <p className="register-label">First Name</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="pencil"/></InputGroup.Addon>
                                    <FormControl type="text" name="firstName" id="firstName" placeholder="Enter your First Name" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formSurname" validationState={this.props.registration.validation.surname}>
                            <p className="register-label">Surname</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="pencil"/></InputGroup.Addon>
                                    <FormControl type="text" name="surname" id="surname" placeholder="Enter your Surname" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formSex" validationState={this.props.registration.validation.sex}>
                            <p className="register-label">Sex</p>
                            <Col sm={12} className="register-radios">
                                <InputGroup>
                                    <InputGroup.Addon><Glyphicon glyph="file"/></InputGroup.Addon>
                                    <div className="register-radio-container">
                                        <Radio name="sex" inline onChange={this.handleChange} >Male</Radio>{' '}
                                        <Radio name="sex" inline onChange={this.handleChange} >Female</Radio>{' '}
                                        <Radio name="sex" inline onChange={this.handleChange} >Attack Helicopter</Radio>
                                    </div>
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formEmail" validationState={this.props.registration.validation.email}>
                            <p className="register-label">Email</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="envelope"/></InputGroup.Addon>
                                    <FormControl type="text" name="email" id="email" placeholder="Enter your Email" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formLogin" validationState={this.props.registration.validation.login}>
                            <p className="register-label">Login</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="user"/></InputGroup.Addon>
                                    <FormControl type="login" name="login" id="login" placeholder="Enter your Login" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formPassword" validationState={this.props.registration.validation.password}>
                            <p className="register-label">Password</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="lock"/></InputGroup.Addon>
                                    <FormControl type="password" name="password" id="password" placeholder="Enter your Password" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formConfirmation" validationState={this.props.registration.validation.confirmation}>
                            <p className="register-label">Confirm Password</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="lock"/></InputGroup.Addon>
                                    <FormControl type="password" name="confirmation" id="confirmation"
                                                 placeholder="Confirm your Password" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup>
                            <Col sm={12} className="register-submit-container">
                                <Button type="submit" disabled={!this.canSubmit} block>Register</Button>
                            </Col>
                        </FormGroup>

                        Already have an account? <Link to="/login">Sign in</Link>!
                    </Form>
                </Panel>
            </div>
        </Grid>;
    }
}

const mapStateToProps = (state) => {
    return {registration: state.registration}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(RegisterContainer);