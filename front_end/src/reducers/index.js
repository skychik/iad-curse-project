// Обработчики

import { combineReducers } from 'redux';
import { pageReducer } from './pageReducer';
import { routerReducer } from 'react-router-redux';
import { feedReducer } from './feedReducer';
import { newsReducer } from './newsReducer';
import { commentsReducer } from './commentsReducer';
import { profileReducer } from "./profileReducer";
import {sessionReducer} from "./sessionReducer";

const reducers = combineReducers({
    session: sessionReducer,
    page: pageReducer,
    feed: feedReducer,
    news: newsReducer,
    comments: commentsReducer,
    profile: profileReducer,
    routing: routerReducer,
});

export default reducers;
