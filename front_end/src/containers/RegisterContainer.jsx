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

class RegisterContainer extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            firstName: null,
            surname: null,
            sex: null,
            email: null,
            login: null,
            password: null,
            confirmation: null,
            validation: {
                firstName: null,
                surname: null,
                sex: null,
                email: null,
                login: null,
                password: null,
                confirmation: null,
            },
            validationStarted: {
                firstName: false,
                surname: false,
                sex: false,
                email: false,
                login: false,
                password: false,
                confirmation: false,
            },
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    startValidation(type) {
        //console.log("startValidation");
        switch (type) {
            case "firstName":
                this.state.validationStarted.firstName = true; break;
            case "surname":
                this.state.validationStarted.surname = true; break;
            case "sex":
                this.state.validationStarted.sex = true; break;
            case "email":
                this.state.validationStarted.email = true; break;
            case "login":
                this.state.validationStarted.login = true; break;
            case "password":
                this.state.validationStarted.password = true; break;
            case "confirmation":
                this.state.validationStarted.confirmation = true; break;
        }
    }

    handleChange(event) {
        event.persist(); // to use debounce
        const validationStarted = this.state.validationStarted;

        switch (event.target.name) {
            case "firstName":
                this.state.firstName = event.target.value;
                if (!validationStarted.firstName) {
                    //console.log("debounce");
                    debounce((() => {
                        this.startValidation("firstName");
                        this.handleChange(event);
                    }), 1000)();
                } else {
                    //console.log("validation");
                    this.state.validation.firstName =
                        validator.isAlphanumeric(event.target.value) ? "success" : "error";
                    console.log(this.state.validation.firstName);
                }
                break;
            case "surname":
                this.state.surname = event.target.value;
                if (!validationStarted.surname) {
                    debounce((() => {
                        this.startValidation("surname");
                        this.handleChange(event);
                    }), 1000)();
                } else {
                    this.state.validation.surname =
                        validator.isAlphanumeric(event.target.value) ? "success" : "error";
                }
                break;
            // case "sex":
            //     this.state.sex = event.target.value;
            //     if (!validationStarted.sex) {
            //         debounce((() => {
            //             this.startValidation("sex");
            //             this.handleChange(event);
            //         }), 1000)();
            //     } else {
            //         this.state.validation.sex =
            //             validator.isAlphanumeric(event.target.value) ? "success" : "error";
            //     }
            //     break;
            case "email":
                this.state.email = event.target.value; break;
            case "login":
                this.state.login = event.target.value; break;
            case "password":
                this.state.password = event.target.value; break;
            case "confirmation":
                this.state.confirmation = event.target.value; break;
        }
        console.log("END " + event.target.name + " " + event.target.value);
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
                        <FormGroup controlId="formFirstName" validationState={this.state.validation.firstName}>
                            {this.state.validation.firstName}
                            <p className="register-label">First Name</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="pencil"/></InputGroup.Addon>
                                    <FormControl type="text" name="firstName" id="firstName" placeholder="Enter your First Name" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formSurname" validationState={this.state.validation.surname}>
                            <p className="register-label">Surname</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="pencil"/></InputGroup.Addon>
                                    <FormControl type="text" name="surname" id="surname" placeholder="Enter your Surname" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formSex" validationState={this.state.validation.sex}>
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

                        <FormGroup controlId="formEmail" validationState={this.state.validation.email}>
                            <p className="register-label">Email</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="envelope"/></InputGroup.Addon>
                                    <FormControl type="text" name="email" id="email" placeholder="Enter your Email" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formLogin" validationState={this.state.validation.login}>
                            <p className="register-label">Login</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="user"/></InputGroup.Addon>
                                    <FormControl type="login" name="login" id="login" placeholder="Enter your Login" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formPassword" validationState={this.state.validation.password}>
                            <p className="register-label">Password</p>
                            <Col sm={12}>
                                <InputGroup className="register-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="lock"/></InputGroup.Addon>
                                    <FormControl type="password" name="password" id="password" placeholder="Enter your Password" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formConfirmation" validationState={this.state.validation.confirmation}>
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