// Обработчики

import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import { feedReducer } from './feedReducer';
import { newsReducer } from './newsReducer';
import { commentsReducer } from './commentsReducer';
import { profileReducer } from "./profileReducer";
import { signinFormReducer } from "./signinFormReducer";
import { redactorReducer } from "./redactorReducer";
import { courseTaskListReducer } from "./courseTaskListReducer";
import {courseTaskReducer} from "./courseTaskReducer";
import {courseBackgroundReducer} from "./courseBackgroundReducer";
import { signinReducer } from "./signinReducer";
import {courseTypesReducer} from "./courseTypesReducer";
import {actionButtonReducer} from "./actionButtonReducer";
import {errorReducer} from "./errorReducer";

const reducers = combineReducers({
    actionButton: actionButtonReducer,
    comments: commentsReducer,
    courseBackground: courseBackgroundReducer,
    courseTaskList: courseTaskListReducer,
    courseTask: courseTaskReducer,
    courseTypes: courseTypesReducer,
    error: errorReducer,
    feed: feedReducer,
    news: newsReducer,
    profile: profileReducer,
    redactor: redactorReducer,
    routing: routerReducer,
    signin: signinReducer,
    signinForm: signinFormReducer,
});

export default reducers;
