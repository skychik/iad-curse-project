// Обработчики

import { combineReducers } from 'redux';
import { pageReducer } from './pageReducer';
import { routerReducer } from 'react-router-redux'

const reducers = combineReducers({
    page: pageReducer,
    routing: routerReducer,
});

export default reducers;