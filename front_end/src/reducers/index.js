// Обработчики

import { combineReducers } from 'redux'
import { pageReducer } from './pageReducer'
import { routerReducer } from 'react-router-redux'
import { feedReducer } from './feedReducer'

const reducers = combineReducers({
    page: pageReducer,
    feed: feedReducer,
    routing: routerReducer,
});

export default reducers;