import React from 'react';
import ReactDOM from 'react-dom';
import './styles/index.css';
//import './styles/bootstrap.css';
//import './styles/sandstone.css';
//import './styles/variables.css';
//import './styles/bootswatch.css';
import AppContainer from './containers/AppContainer';
import registerServiceWorker from './registerServiceWorker'; // for offline
import { Provider } from 'react-redux'
import reducers from "./reducers/index"
import MainContainer from './containers/MainContainer'
import Welcome from './components/Welcome'
import LoginContainer from "./containers/LoginContainer"
import {applyMiddleware, createStore} from "redux"
import thunk from "redux-thunk"
import promise from "redux-promise-middleware"
import createHistory from 'history/createBrowserHistory'
import { ConnectedRouter } from 'react-router-redux'
import { composeWithDevTools } from 'redux-devtools-extension';
import PageNotFound from "./components/PageNotFound";
// import RestClient from "another-rest-client";

// function makeRestApi () {
//     return new RestClient('http://localhost:8080').res({
//         user: 0,
//     });
// }

const store = createStore(
    reducers,
    { // initial state
        signinForm: {
            firstName: null,
            surname: null,
            sex: null,
            email: null,
            login: null,
            password: null,
            confirmation: null,
            registrationSubmitEnabled: false,
            signinSubmitEnabled: false,
            formChanged: true,
            validation: {
                firstName: false,
                surname: false,
                sex: false,
                email: false,
                loginCorrect: false,
                loginExists: false,
                password: false,
                confirmation: false,
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
        },
        courseBackground: false,
        feed: null,
        news: null,
        newsMaker: {
            content: null,
            title: null,
            answer: null,
        },
        comments: null,
        profile: null,
        routing: null,
    }, composeWithDevTools(applyMiddleware(promise(), thunk)));

const history = createHistory();

ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <MainContainer/>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
);

registerServiceWorker();

