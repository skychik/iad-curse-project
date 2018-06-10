export function profileReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_PROFILE_FULFILLED":
            return {...state, info: action.payload};
        case "FETCH_USER_NEWS_LIST_FULFILLED":
            return {...state, newsList: action.payload};
        case "FETCH_PROFILE_COURSES_FULFILLED":
            return {...state, courses: action.payload};

        case "SUBSCRIBE_COURSE_FULFILLED":
            return {...state, courses: state.courses.map((course) =>
                    action.payload === course.id ? {...course, subscribed: true} : course)};
        case "UNSUBSCRIBE_COURSE_FULFILLED":
            return {...state, courses: state.courses.map((course) =>
                    action.payload === course.id ? {...course, subscribed: false} : course)};
    }
    return state;
}