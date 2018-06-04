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

const reducers = combineReducers({
    signinForm: signinFormReducer,
    feed: feedReducer,
    news: newsReducer,
    redactor: redactorReducer,
    comments: commentsReducer,
    profile: profileReducer,
    routing: routerReducer,
//    newsPLoopFeedback: newsPLoopReducer,
    courseTaskList: courseTaskListReducer,
    courseTask: courseTaskReducer,
    courseBackground: courseBackgroundReducer,
});

export default reducers;
