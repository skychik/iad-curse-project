import React from 'react';
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

class CoursesContainer extends React.Component {
    componentWillMount() {
        this.props.setCourseBackground(true);
    }

    render() {
        return (
            <div className="CoursesContainer">
                <h2>{this.props.content}</h2>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {redactor: state.redactor}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(CoursesContainer);