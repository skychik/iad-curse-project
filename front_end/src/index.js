import React from 'react';
import ReactDOM from 'react-dom';
import './styles/index.css';
import App from './containers/App';
import registerServiceWorker from './registerServiceWorker'; // for offline
import { Provider } from 'react-redux'
import reducers from "./reducers"
import MainContainer from './containers/MainContainer'
import Welcome from './components/welcome'
import { createStore } from "redux"
import { Route, Switch, Redirect } from 'react-router-dom'
import createHistory from 'history/createBrowserHistory'
import {ConnectedRouter, routerReducer} from 'react-router-redux'
import PageNotFound from "./components/pageNotFound";
import {pageReducer} from "./reducers/pageReducer";
import {restApiReducer} from "./reducers/restApiReducer";

const store = createStore(
    reducers,
    {
        page: null,
        content: null,
        routing: null,
    });
const history = createHistory();

ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <MainContainer>
                <Switch>
                    <Route exact path='/' render={() => <Redirect to='welcome' />}/>
                    <Route path='/welcome' component={Welcome} />
                    <Route path='/pile' component={App} />
                    <Route path='/events' component={App} />
                    <Route path='/mentors' component={App} />
                    <Route path='/loops' component={App} />
                    <Route path='/profile' component={App} />
                    <Route exact path='/page_not_found' component={PageNotFound}/>
                    <Route render={() => <Redirect to='page_not_found' /> } />
                </Switch>
            </MainContainer>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
);

registerServiceWorker();

