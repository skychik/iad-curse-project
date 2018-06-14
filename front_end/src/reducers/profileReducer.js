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
        case "FOLLOW_USER_FULFILLED":
            return {...state, info: {...state.info, subscribed: true}};
        case "UNFOLLOW_USER_FULFILLED":
            return {...state, info: {...state.info, subscribed: false}};

        case "PUT_NEWS_LOOP_FULFILLED":
            if (state.newsList == null) break;
            return {...state, newsList: state.newsList.map((news) =>
                    action.payload.id === news.id ? {...news, loopsNumber: action.payload.value, loopWasPut: true}: news)};
        case "PUT_NEWS_POOP_FULFILLED":
            if (state.newsList == null) break;
            return {...state, newsList: state.newsList.map((news) =>
                    action.payload.id === news.id ? {...news, poopsNumber: action.payload.value, poopWasPut: true}: news)};
        case "REMOVE_NEWS_LOOP_FULFILLED":
            if (state.newsList == null) break;
            return {...state, newsList: state.newsList.map((news) =>
                    action.payload.id === news.id ? {...news, loopsNumber: action.payload.value, loopWasPut: false}: news)};
        case "REMOVE_NEWS_POOP_FULFILLED":
            if (state.newsList == null) break;
            return {...state, newsList: state.newsList.map((news) =>
                    action.payload.id === news.id ? {...news, poopsNumber: action.payload.value, poopWasPut: false}: news)};

    }
    return state;
}