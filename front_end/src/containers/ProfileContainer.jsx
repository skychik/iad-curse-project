import React  from 'react';
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import RestClient from "another-rest-client";

class Profile extends React.Component {
    componentDidMount() {
        let api = new RestClient('http://localhost:8080'); // TODO: make static
        const promise = api.res("profile").res(2).get();
        promise.then((response) => {
            const profile = JSON.parse(JSON.stringify(response));
            this.setProfile(profile);
        });
    }

    setProfile(profile) {
        return this.props.setProfile(profile)
    }

    render() {
        const profile = this.props.profile;
        return (
            <div className="Profile">
                <h2>{profile}</h2>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {profile: state.profile}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(Profile);