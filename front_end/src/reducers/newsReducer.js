export function newsReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_NEWS_PENDING": break;
        case "ADD_COMMENT_PENDING": break;
        case "FETCH_NEWS_FULFILLED":
            return {...action.payload, addCommentShowed: false, commentingComm: null, newCommentContent: ""};
        case "FETCH_NEWS_REJECTED":
            console.log(action.payload);
            break;
        case "PUT_NEWS_LOOP_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, loopsNumber: action.payload.value, loopWasPut: action.payload.succeed}: state;
        case "PUT_NEWS_POOP_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, poopsNumber: action.payload.value, poopWasPut: action.payload.succeed}: state;
        case "REMOVE_NEWS_LOOP_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, loopsNumber: action.payload.value, loopWasPut: !action.payload.succeed}: state;
        case "REMOVE_NEWS_POOP_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, poopsNumber: action.payload.value, poopWasPut: !action.payload.succeed}: state;

        case "SHOW_ADD_COMMENT":
            return {...state, addCommentShowed: true, commentingComm: action.payload};
        case "ADD_COMMENT_FULFILLED":
            window.location.reload();
            return state;
        case "HIDE_ADD_COMMENT":
            return {...state, addCommentShowed: false};
        case "SET_NEW_COMMENT_CONTENT":
            return {...state, newCommentContent: action.payload};
        default:
            return state;
    }
    return state;
}