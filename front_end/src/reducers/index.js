// Обработчики

import { combineReducers } from 'redux';
import { pageReducer } from './pageReducer';
import { routerReducer } from 'react-router-redux';
import { feedReducer } from './feedReducer';
import { newsReducer } from './newsReducer';
import {profileReducer} from "./profileReducer";

const reducers = combineReducers({
    page: pageReducer,
    feed: feedReducer,
    news: newsReducer,
    profile: profileReducer,
    routing: routerReducer,
});

export default reducers;
