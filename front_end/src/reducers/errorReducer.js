export function errorReducer(state = {}, action) {
    switch (action.type) {
        case "DISMISS_ERROR":
            let newState1 = state.slice();
            newState1.splice(state.indexOf(action.payload), 1);
            return newState1;
        case "FETCH_FEED_REJECTED":
        case "FETCH_NEWS_REJECTED":
        case "FETCH_PROFILE_REJECTED":
        case "FETCH_USER_NEWS_LIST_REJECTED":
        case "FETCH_PROFILE_COURSES_REJECTED":
        case "FETCH_COMMENTS_REJECTED":
        case "NEWS_LOOP_RECEIVED_REJECTED":
        case "NEWS_POOP_RECEIVED_REJECTED":
        case "COMMENTS_LOOP_RECEIVED_REJECTED":
        case "COMMENTS_POOP_RECEIVED_REJECTED":
        case "FETCH_COURSE_TASKS_REJECTED":
        case "IS_USER_NEWS_TITLE_EXISTS_REJECTED":
        case "FETCH_USER_COURSES_REJECTED":
        case "FETCH_COURSE_TYPES_REJECTED":
        case "SIGNIN_REJECTED":
        case "ADD_COMMENT_REJECTED":
        case 'ADD_NEW_COURSE_TASK_REJECTED':
        case 'INIT_COURSE_WITH_TASK_REJECTED':
        case "POST_NEWS_REJECTED":
        case "POST_COURSE_TASK_REJECTED":
        case "POST_INIT_COURSE_WITH_TASK_REJECTED":
        case "FOLLOW_USER_REJECTED":
        case "UNFOLLOW_USER_REJECTED":
            let newState = state.slice();
            newState.push({id: state.length, message: action.payload.response});
            return newState;
        case "PUT_NEWS_LOOP_REJECTED":
        case "PUT_NEWS_POOP_REJECTED":
        case "REMOVE_NEWS_LOOP_REJECTED":
        case "REMOVE_NEWS_POOP_REJECTED":
        case "PUT_COMMENT_LOOP_REJECTED":
        case "PUT_COMMENT_POOP_REJECTED":
        case "REMOVE_COMMENT_LOOP_REJECTED":
        case "REMOVE_COMMENT_POOP_REJECTED":
        case "PUT_COURSE_TASK_LOOP_REJECTED":
        case "PUT_COURSE_TASK_POOP_REJECTED":
        case "REMOVE_COURSE_TASK_LOOP_REJECTED":
        case "REMOVE_COURSE_TASK_POOP_REJECTED":
        case "COMPLETE_COURSE_TASK_REJECTED":
        case "UNDO_COURSE_TASK_REJECTED":
        case "SUBSCRIBE_COURSE_REJECTED":
        case "UNSUBSCRIBE_COURSE_REJECTED":
            let newState2 = state.slice();
            newState2.push({id: state.length, message: action.payload.response});
            return newState2;
    }
    return state;
}