import React from 'react';
import {connect} from "react-redux";
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import {Redirect, Route, Switch} from "react-router-dom";
import CreateNewsContainer from "./CreateNewsContainer";
import CreateCourseTaskContainer from "./CreateCourseTaskContainer";

class CreateContainer extends React.Component {
    render() {
        return <Switch>
                <Route exact path='/create' render={() => <Redirect to='/feed' />}/>
                <Route path='/create/news' component={CreateNewsContainer}/>
                <Route path='/create/course' component={CreateCourseTaskContainer}/>
            </Switch>
    }
}

const mapStateToProps = (state) => {
    return {newsMaker: state.newsMaker}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(CreateContainer);