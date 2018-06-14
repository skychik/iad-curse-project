import React  from 'react';
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import RestClient from "another-rest-client";
import {
    Button,
    Col,
    Form,
    FormControl,
    FormGroup,
    Glyphicon,
    Image,
    InputGroup, Modal,
    PageHeader, Radio,
    Row, Well, FieldGroup
} from "react-bootstrap";
import {debounce} from "lodash";
import ChangeField from "../components/ChangeField";
import {restApiUrl} from "../options";

class ProfileSettingsContainer extends React.Component {
    componentDidMount() {
        console.log(parseInt(this.props.match.params.number, 10));
        this.props.setProfile(parseInt(this.props.match.params.number, 10));
        this.props.setNewsListByUserId(parseInt(this.props.match.params.number, 10));
        this.props.setCoursesByUserId(parseInt(this.props.match.params.number, 10));
    }

    validate(target, validationStarted) {
        if (validationStarted) {
            switch (target.name) {
                case "sex":
                    this.props.validateContent(target.id);
                    break;
                case "login":
                    this.props.doesUsernameExist(target.value);
                    this.props.validateContent(target.value);
                    break;
                default:
                    this.props.validateContent(target.value);
            }
        } else {
            debounce(() => {
                this.props.startValidation(null);
                this.validate(target, true);
            }, 1000)();
        }
    }

    handleChange(event) {
        const started = this.props.profileSettings.validationStarted;
        switch (event.target.name) {
            case "firstName":
                this.validate(event.target, started); break;
            case "surname":
                this.validate(event.target, started); break;
            case "patronymic":
                this.validate(event.target, started); break;
            case "sex":
                console.log("sex=" + event.target.id);
                this.validate(event.target, started); break;
            case "email":
                this.validate(event.target, started); break;
            case "login":
                this.validate(event.target, started); break;
            case "password":
                this.validate(event.target, started); break;
            case "confirmation":
                this.validate(event.target, started); break;
            default:
        }
    }

    handleSubmit(event) {
        if (this.props.profileSettings.isValid) {
            this.props.changeProfileInfo(this.props.profileSettings.type, this.props.profileSettings.content);
            this.props.hideChangeSetting();
        }

        event.preventDefault();
    }

    render() {
        const {profile, profileSettings} = this.props;

        return <div>
            <PageHeader>Settings</PageHeader>
            <ChangeField header={"First Name"} type={"firstName"} content={profile.info.firstName}
                         onClick={this.props.showChangeSetting}/>
            <ChangeField header={"Surname"} type={"surname"} content={profile.info.surname}
                         onClick={this.props.showChangeSetting}/>
            {/*<ChangeField header={"Patronymic"} type={"patronymic"} content={profile.info.patronymic} */}
                         {/*onClick={this.props.showChangeSetting}/>*/}
            <ChangeField header={"Sex"} type={"sex"} content={profile.info.sex}
                         onClick={this.props.showChangeSetting}/>
            {/*<ChangeField header={"Email"} type={"email"} content={profile.info.email}*/}
                         {/*onClick={this.props.showChangeSetting}/>*/}
            <ChangeField header={"Username"} type={"username"} content={profile.info.username}
                         onClick={this.props.showChangeSetting}/>
            <ChangeField header={"Password"} type={"password"}
                         onClick={this.props.showChangeSetting}/>

            <Modal show={profileSettings.isModalShown} onHide={this.props.hideChangeSetting}
                       bsSize="large" aria-labelledby="contained-modal-title-lg">
                <Form onSubmit={this.handleSubmitNewComment}>
                    <Modal.Header closeButton>
                        <Modal.Title id="contained-modal-title-sm">{"Change " + profileSettings.header}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <FormGroup controlId="formControlsTextarea">
                            <div>
                                <Well style={{overflow: "hidden", textOverflow: "ellipsis"}}>
                                    {profileSettings.header + ": "}{profileSettings.type === "sex" ?
                                    profileSettings.previousContent == null ? "Attack Helicopter" : profileSettings.previousContent ? "Male" : "Female"
                                    : profileSettings.previousContent}
                                </Well>
                            </div>
                            {profileSettings.header === "sex" ?
                                <InputGroup>
                                    <div className="register-radio-container">
                                        <Radio name="sex" id="male" inline onChange={this.handleChange}>
                                            Male
                                        </Radio>{' '}
                                        <Radio name="sex" id="female" inline onChange={this.handleChange}>
                                            Female
                                        </Radio>{' '}
                                        <Radio name="sex" id="helicopter" inline onChange={this.handleChange}>
                                            Attack Helicopter
                                        </Radio>
                                    </div>
                                    <FormControl.Feedback />
                                </InputGroup>
                                : profileSettings.header === "email" ?
                                    <InputGroup className="register-input-group">
                                        <FormControl type="email" name="email" placeholder="Enter your Email"
                                                     onChange={this.handleChange} />
                                        <FormControl.Feedback />
                                    </InputGroup>
                                : profileSettings.header === "login" ?
                                <InputGroup className="register-input-group">
                                    <FormControl type="login" name="login" placeholder="Enter your Login"
                                                 onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                                : profileSettings.header === "password" ?
                                <InputGroup className="register-input-group">
                                    <FormControl type="password" name="password"
                                                 placeholder="Enter your Password" onChange={this.handleChange} />
                                    <FormControl type="password" name="confirmation"
                                                 placeholder="Confirm your Password" onChange={this.handleChange} />
                                    <FormControl.Feedback />
                                </InputGroup>
                                : <InputGroup className="register-input-group">
                                    <FormControl type="text" placeholder={profileSettings.previousContent}
                                                 name={profileSettings.type} style={{resize: "none"}} rows="10"
                                                 onChange={this.props.onChange} autoFocus/>
                                    <FormControl.Feedback />
                                </InputGroup>}
                        </FormGroup>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button type="submit" onClick={this.handleSubmit} disabled={!profileSettings.isValid}>Send</Button>
                        <Button onClick={this.props.hideChangeSetting}>Close</Button>
                    </Modal.Footer>
                </Form>
            </Modal>
        </div>
    }
}

const mapStateToProps = (state) => {
    return {
        profile: state.profile,
        profileSettings: state.profileSettings,
    }
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(ProfileSettingsContainer);