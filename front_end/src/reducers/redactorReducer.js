export function redactorReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_COURSE_TYPES_FULFILLED":
            return {...state, courseTypes: action.payload};
        case "FETCH_USER_COURSES_FULFILLED":
            return {...state, userCourses: action.payload};
        case "IS_USER_NEWS_TITLE_EXISTS":
            return {...state, isUserTitleExists: action.payload};

        case "SET_IS_NEW_COURSE":
            return {...state, input: {...state.input, isNewCourse: action.payload}};
        case "REDACTOR_NEWS_COURSE_TITLE_CHANGED":
            return {...state, input: {...state.input, courseTitle: action.payload}};
        case "REDACTOR_NEWS_COURSE_TYPE_CHANGED":
            return {...state, input: {...state.input, courseType: action.payload}};
        case "REDACTOR_NEWS_CHOOSE_COURSE_ID":
            return {...state, input: {...state.input, chosenCourseId: action.payload}};
        case "REDACTOR_NEWS_TITLE_CHANGED":
            return {...state, input: {...state.input, newsTitle: action.payload}};
        case "REDACTOR_TASK_TITLE_CHANGED":
            return {...state, input: {...state.input, taskTitle: action.payload}};
        case "REDACTOR_NEWS_CONTENT_CHANGED":
            return {...state, input: {...state.input, content: action.payload}};
        case "POST_NEWS_FULFILLED":
            return {...state, answer: action.payload};
        case "POST_COURSE_TASK_FULFILLED":
            return {...state, answer: action.payload};
        case "POST_INIT_COURSE_WITH_TASK_FULFILLED":
            return {...state, answer: action.payload};
        default:
            return state;
    }
}