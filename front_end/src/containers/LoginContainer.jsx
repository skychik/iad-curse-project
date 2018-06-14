import React from "react";
import {
    Button,
    Col,
    Form,
    FormControl,
    FormGroup, Glyphicon,
    Grid,
    InputGroup,
    Panel
} from "react-bootstrap";
import {Link} from "react-router-dom";
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {debounce} from "lodash";
import RegisterContainer from "./RegisterContainer";
import cookie from 'react-cookies';
import {Redirect} from "react-router-dom";

class LoginContainer extends React.Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentWillMount() {
        this.props.initValidation();
        cookie.save("userId", "", {path: "/"});
    }

    // setUserId(response) {
    //     this.props.setUserId(response);
    // }

    validate(target, isValidationStarted) {
        if (isValidationStarted) {
            switch (target.name) {
                case "login":
                    this.props.setUserAttribute({
                        name: "loginCorrect",
                        value: target.value,
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

    handleChange(event) {
        switch (event.target.name) {
            case "login":
                this.validate(event.target, this.props.signinForm.validationStarted.login); break;
            case "password":
                this.validate(event.target, this.props.signinForm.validationStarted.password); break;
        }
    }

    handleSubmit(event) {
        if (this.props.signinForm.signinSubmitEnabled) {
            this.props.initValidation();

            this.props.doSignin(this.props.signinForm.login, this.props.signinForm.password);
        }

        event.preventDefault();
    }

    render() {
        console.log("this.props.signin.success");
        console.log(this.props.signin.success);
        if (this.props.signin.success) {
            return <Redirect to="/feed"/>
        }

        return <Grid className="LoginContainer">
            <div className="login-inner">
                <h1>Muzloop</h1>
                <Panel className="login-form-container">
                    <h4 className="login-title">Sign in</h4>
                    <Form horizontal onSubmit={this.handleSubmit}>
                        <FormGroup controlId="formLogin" validationState={
                                RegisterContainer.makeValidationState(this.props.signinForm.validationStarted.login,
                                !this.props.signinForm.validation.loginExists && this.props.signinForm.validation.loginCorrect)}>
                            <p className="login-label">Login</p>
                            <Col sm={12} xs={12}>
                                <InputGroup className="login-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="user"/></InputGroup.Addon>
                                    <FormControl type="login" name="login" placeholder="Enter your Login"
                                                 onChange={this.handleChange}/>
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formPassword" validationState={
                                RegisterContainer.makeValidationState(this.props.signinForm.validationStarted.password,
                                this.props.signinForm.validation.password)}>
                            <p className="login-label">Password</p>
                            <Col sm={12} xs={12}>
                                <InputGroup className="login-input-group">
                                    <InputGroup.Addon><Glyphicon glyph="lock"/></InputGroup.Addon>
                                    <FormControl type="password" name="password" placeholder="Enter your Password"
                                                 onChange={this.handleChange}/>
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        {this.props.signin.pending ? <span>trying to signin...</span> :
                            !this.props.signin.success ? this.props.signin.success === false ?
                                <span className="login-correctness">Error: {this.props.signin.answer}</span> : null : null}

                        <FormGroup>
                            <Col sm={12} xs={12}>
                                <Button type="submit" disabled={!this.props.signinForm.signinSubmitEnabled} block>
                                    Sign in
                                </Button>
                            </Col>
                        </FormGroup>

                        Haven't <Link to="/register">registered</Link> yet?
                    </Form>
                </Panel>
            </div>
        </Grid>;
    }
}

const mapStateToProps = (state) => {
    return {
        signin: state.signin,
        signinForm: state.signinForm,
    }
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(LoginContainer);