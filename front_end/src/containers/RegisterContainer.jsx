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
import {debounce} from "lodash";
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import RestClient from "another-rest-client";
import {restApiUrl} from "../options";

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
        this.props.initValidation();
    }

    setRegistrationSuccess(ans) {
        return this.props.setRegistrationSuccess(ans);
    }

    validate(target, isValidationStarted) {
        if (isValidationStarted) {
            switch (target.name) {
                case "sex":
                    this.props.setUserAttribute({
                        name: target.name,
                        value: target.id,
                    });
                    break;
                case "login":
                    this.props.setUserAttribute({
                        name: "loginCorrect",
                        value: target.value,
                    });

                    let api = new RestClient(restApiUrl);
                    const promise = api.res("user").res("doesExist").get({username: target.value});
                    promise.then((response) => {
                        console.log("doesLoginExist=" + response);
                        this.props.setUserAttribute({
                            name: "loginExists",
                            value: response,
                        });
                    });
                    break;
                default:
                    this.props.setUserAttribute({
                        name: target.name,
                        value: target.value,
                    });
            }
        } else {
            debounce(() => {
                this.props.startValidation(target.name);
                this.validate(target, true);
            }, 1000)();
        }
    }

    static makeValidationState(validationStarted, isValid) {
        if (!validationStarted) return null;
        return isValid ? "success" : "error";
    }

    handleChange(event) {
        switch (event.target.name) {
            case "firstName":
                this.validate(event.target, this.props.signinForm.validationStarted.firstName); break;
            case "surname":
                this.validate(event.target, this.props.signinForm.validationStarted.surname); break;
            // case "patronymic":
            //     this.validate(event.target, this.props.signinForm.validationStarted.patronymic); break;
            case "sex":
                console.log("sex=" + event.target.id);
                this.validate(event.target, this.props.signinForm.validationStarted.sex); break;
            case "email":
                this.validate(event.target, this.props.signinForm.validationStarted.email); break;
            case "login":
                this.validate(event.target, this.props.signinForm.validationStarted.login); break;
            case "password":
                this.validate(event.target, this.props.signinForm.validationStarted.password); break;
            case "confirmation":
                this.validate(event.target, this.props.signinForm.validationStarted.confirmation); break;
            default:
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

    handleSubmit(event) {
        console.log("register");

        if (this.props.signinForm.registrationSubmitEnabled) {
            let api = new RestClient('http://localhost:8080');
            const promise = api.res("user").res("register").post(this.props.signinForm);
            promise.then((response) => {
                this.setRegistrationSuccess(response);
            });

            this.props.history.push("/login");
        }

        event.preventDefault();
    }

    render() {
        const signinForm = this.props.signinForm;

        return <Grid className="RegisterContainer">
            <div className="register-inner">
                <h1>Muzloop</h1>
                <Panel className="register-form-container">
                    <h4 className="register-title">Register</h4>
                    <Form horizontal onSubmit={this.handleSubmit}>
                        <FormGroup controlId="formFirstName" validationState={
                                RegisterContainer.makeValidationState(signinForm.validationStarted.firstName, signinForm.validation.firstName)}>
                            <p className="register-label">* First Name</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="pencil"/></InputGroup.Addon>
                                    <FormControl type="text" name="firstName" placeholder="Enter your First Name" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formSurname" validationState={
                                RegisterContainer.makeValidationState(signinForm.validationStarted.surname, signinForm.validation.surname)}>
                            <p className="register-label">* Surname</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="pencil"/></InputGroup.Addon>
                                    <FormControl type="text" name="surname" placeholder="Enter your Surname" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formPatronymic">
                            <p className="register-label">Patronymic</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="pencil"/></InputGroup.Addon>
                                    <FormControl type="text" name="patronymic" placeholder="Enter your Patronymic" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formSex" validationState={
                                RegisterContainer.makeValidationState(signinForm.validationStarted.sex, signinForm.validation.sex)}>
                            <p className="register-label">* Sex</p>
                            <Col sm={12} className="register-radios">
                                <InputGroup>
                                    <InputGroup.Addon><Glyphicon glyph="file"/></InputGroup.Addon>
                                    <div className="register-radio-container">
                                        <Radio name="sex" id="male" inline onChange={this.handleChange} >Male</Radio>{' '}
                                        <Radio name="sex" id="female" inline onChange={this.handleChange} >Female</Radio>{' '}
                                        <Radio name="sex" id="helicopter" inline onChange={this.handleChange} >Attack Helicopter</Radio>
                                    </div>
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formEmail" validationState={
                                RegisterContainer.makeValidationState(signinForm.validationStarted.email, signinForm.validation.email)}>
                            <p className="register-label">* Email</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="envelope"/></InputGroup.Addon>
                                    <FormControl type="text" name="email" placeholder="Enter your Email" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formLogin" validationState={
                                RegisterContainer.makeValidationState(signinForm.validationStarted.login,
                                    !signinForm.validation.loginExists && signinForm.validation.loginCorrect)}>
                            <p className="register-label">* Login (3 or more symbols)
                                {this.props.signinForm.validation.loginExists ?
                                    <span className="register-login-existence">This login already exists!</span> : null}
                            </p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="user"/></InputGroup.Addon>
                                    <FormControl type="login" name="login" placeholder="Enter your Login" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formPassword" validationState={
                                RegisterContainer.makeValidationState(signinForm.validationStarted.password, signinForm.validation.password)}>
                            <p className="register-label">* Password (6 or more symbols)</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="lock"/></InputGroup.Addon>
                                        <FormControl type="password" name="password" placeholder="Enter your Password" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formConfirmation" validationState={
                                RegisterContainer.makeValidationState(signinForm.validationStarted.confirmation, signinForm.validation.confirmation)}>
                            <p className="register-label">* Confirm Password</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="lock"/></InputGroup.Addon>
                                    <FormControl type="password" name="confirmation"
                                                 placeholder="Confirm your Password" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup>
                            <Col sm={12} className="register-submit-container">
                                <Button type="submit" disabled={!this.props.signinForm.registrationSubmitEnabled} block>Register</Button>
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
    return {
        signinForm: state.signinForm,
    }
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(RegisterContainer);