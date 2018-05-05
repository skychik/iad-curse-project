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
import { createStore } from "redux"
import createHistory from 'history/createBrowserHistory'
import { ConnectedRouter } from 'react-router-redux'
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
        registration: null,
        session: null,
        page: null,
        feed: null,
        news: null,
        comments: null,
        profile: null,
        routing: null,
    }, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

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

