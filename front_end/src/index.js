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
import Welcome from './components/welcome'
import { createStore } from "redux"
import { Route, Switch, Redirect } from 'react-router-dom'
import createHistory from 'history/createBrowserHistory'
import {ConnectedRouter} from 'react-router-redux'
import PageNotFound from "./components/PageNotFound";
import NewsContainer from "./containers/NewsContainer";

const store = createStore(
    reducers,
    { // initial state
        page: null,
        feed: null,
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
                    <Route path='/feed' component={AppContainer} />
                    <Route path='/news/:number' component={AppContainer} />
                    <Route path='/events' component={AppContainer} />
                    <Route path='/mentors' component={AppContainer} />
                    <Route path='/loops' component={AppContainer} />
                    <Route path='/profile' component={AppContainer} />
                    <Route exact path='/page_not_found' component={PageNotFound}/>
                    <Route render={() => <Redirect to='/page_not_found' /> } />
                </Switch>
            </MainContainer>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
);

registerServiceWorker();

