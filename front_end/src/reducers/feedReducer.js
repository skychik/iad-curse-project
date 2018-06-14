export function feedReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_FEED_PENDING":
            return {...state, pending: true};
        case "FETCH_FEED_FULFILLED": return {...state, data: action.payload, pending: false};

        case "PUT_NEWS_LOOP_FULFILLED":
            if (state.data == null) break;
            return {...state, data: state.data.map((news) =>
                action.payload.id === news.id ? {...news, loopsNumber: action.payload.value, loopWasPut: true}: news)};
        case "PUT_NEWS_POOP_FULFILLED":
            if (state.data == null) break;
            return {...state, data: state.data.map((news) =>
                action.payload.id === news.id ? {...news, poopsNumber: action.payload.value, poopWasPut: true}: news)};
        case "REMOVE_NEWS_LOOP_FULFILLED":
            if (state.data == null) break;
            return {...state, data: state.data.map((news) =>
                action.payload.id === news.id ? {...news, loopsNumber: action.payload.value, loopWasPut: false}: news)};
        case "REMOVE_NEWS_POOP_FULFILLED":
            if (state.data == null) break;
            return {...state, data: state.data.map((news) =>
                action.payload.id === news.id ? {...news, poopsNumber: action.payload.value, poopWasPut: false}: news)};

        case "FETCH_FEED_REJECTED": return {...state, pending: false};
        case "SHOW_ADD_COMMENT": return {...state, addCommentShowed: true, commentingComm: action.payload};
        case "HIDE_ADD_COMMENT": return {...state, addCommentShowed: false};
        case "SET_NEW_COMMENT_CONTENT": return {...state, newCommentContent: action.payload};
    }
    return state;
}