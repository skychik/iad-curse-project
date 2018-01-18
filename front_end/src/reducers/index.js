// Обработчики

import { combineReducers } from 'redux'
import { pageReducer } from './pageReducer'
import { routerReducer } from 'react-router-redux'
import { restApiReducer } from './restApiReducer'

const reducers = combineReducers({
    page: pageReducer,
    content: restApiReducer,
    routing: routerReducer,
});

export default reducers;