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
    InputGroup,
    Panel
} from "react-bootstrap";
import {Link} from "react-router-dom";

class LoginContainer extends React.Component {
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
        return <Grid className="LoginContainer">
            <div className="login-inner">
                <h1>Muzloop</h1>
                <Panel className="login-form-container">
                    <h4 className="login-title">Sign in</h4>
                    <Form horizontal>
                        <FormGroup controlId="formLogin">
                            <p className="login-label">
                                Login
                            </p>
                            <Col sm={12} xs={12}>
                                <InputGroup className="login-input-group">
                                    <InputGroup.Addon>
                                        <Glyphicon glyph="user"/>
                                    </InputGroup.Addon>
                                    <FormControl type="login" placeholder="Login" onChange={this.handleChange} />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        <FormGroup controlId="formPassword">
                            <p className="login-label">
                                Password
                            </p>
                            <Col sm={12} xs={12}>
                                <InputGroup className="login-input-group">
                                    <InputGroup.Addon>
                                        <Glyphicon glyph="lock"/>
                                    </InputGroup.Addon>
                                    <FormControl type="password" placeholder="Password" />
                                </InputGroup>
                            </Col>
                        </FormGroup>

                        {/*<FormGroup>*/}
                            {/*<Col smOffset={4} sm={6}>*/}
                                {/*<Checkbox>Remember me</Checkbox>*/}
                            {/*</Col>*/}
                        {/*</FormGroup>*/}

                        <FormGroup>
                            <Col sm={12} xs={12}>
                                <Button type="submit" block>Sign in</Button>
                            </Col>
                        </FormGroup>

                        Haven't <Link to="/register">registered</Link> yet?
                    </Form>
                </Panel>
            </div>
        </Grid>;
    }
}

export default LoginContainer;