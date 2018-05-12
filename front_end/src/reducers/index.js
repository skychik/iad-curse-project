// Обработчики

import { combineReducers } from 'redux';
import { pageReducer } from './pageReducer';
import { routerReducer } from 'react-router-redux';
import { feedReducer } from './feedReducer';
import { newsReducer } from './newsReducer';
import { commentsReducer } from './commentsReducer';
import { profileReducer } from "./profileReducer";
import { mySessionReducer } from "./mySessionReducer";
import { signinFormReducer } from "./signinFormReducer";

const reducers = combineReducers({
    signinForm: signinFormReducer,
    session: mySessionReducer,
    page: pageReducer,
    feed: feedReducer,
    news: newsReducer,
    comments: commentsReducer,
    profile: profileReducer,
    routing: routerReducer,
});

export default reducers;
