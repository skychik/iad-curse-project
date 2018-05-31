import React from 'react';
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import Welcome from "../components/Welcome";
import AppContainer from "./AppContainer";
import PageNotFound from "../components/PageNotFound";
import LoginContainer from "./LoginContainer";
import {Route, Switch, Redirect, withRouter} from 'react-router-dom';
import { userIsAuthenticated } from '../auth'
import RegisterContainer from "./RegisterContainer";
import cookie from "react-cookies";

class MainContainer extends React.Component {
    //newsRendering = null;

    render() {
        // let promise = Promise.defer();
        // this.newsRendering = this.asyncRenderNews(null);
        // asyncGetNews()
        //     .then((news) => asyncRenderNews(news))
        //
        //     .catch((error) => console.log("Error occured: " + error.message));
        console.log("is cookie correct=" + ((cookie.load("userId") !== undefined) && (cookie.load("userId") !== "") && (cookie.load("userId") !== null)));

        return (
            <div className="MainContainer">
                <Switch>
                    <Route exact path='/' render={() => <Redirect to='welcome' />}/>
                    <Route path='/welcome' component={Welcome} />
                    <Route path='/feed' component={userIsAuthenticated(AppContainer)} />
                    <Route path='/create' component={userIsAuthenticated(AppContainer)} />
                    <Route path='/news/:number' component={AppContainer} />
                    <Route path='/events' component={userIsAuthenticated(AppContainer)} />
                    <Route path='/courses' component={userIsAuthenticated(AppContainer)} />
                    <Route path='/loops' component={userIsAuthenticated(AppContainer)} />
                    <Route path="/id" component={userIsAuthenticated(AppContainer)} /> // change to wall or smth; login in url, not id
                    <Route path="/login" component={LoginContainer} />
                    <Route path="/register" component={RegisterContainer} />
                    <Route exact path='/page_not_found' component={PageNotFound}/>
                    <Route render={() => <Redirect to='/page_not_found' /> } />
                </Switch>
            </div>
        );
    }
}

// const mapStateToProps = (state) => {
//     return {session: state.session}
// };

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default withRouter(connect(undefined, mapDispatchToProps)(MainContainer));
