export function newsReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_NEWS_PENDING": return {...action.payload, pending: true};
        case "FETCH_NEWS_FULFILLED":
            return {...action.payload, pending: false, addCommentShowed: false, commentingComm: null, newCommentContent: ""};
        case "FETCH_NEWS_REJECTED":
            return {...action.payload, pending: false};

        case "PUT_NEWS_LOOP_FULFILLED":
            console.log("message:");
            console.log(action.payload);
            if (state == null) break;
            return action.payload.id === state.id ? {...state, loopsNumber: action.payload.value, loopWasPut: true}: state;
        case "PUT_NEWS_POOP_FULFILLED":
            console.log("message:");
            console.log(action.payload);
            if (state == null) break;
            return action.payload.id === state.id ? {...state, poopsNumber: action.payload.value, poopWasPut: true}: state;
        case "REMOVE_NEWS_LOOP_FULFILLED":
            console.log("message:");
            console.log(action.payload);
            if (state == null) break;
            return action.payload.id === state.id ? {...state, loopsNumber: action.payload.value, loopWasPut: false}: state;
        case "REMOVE_NEWS_POOP_FULFILLED":
            console.log("message:");
            console.log(action.payload);
            if (state == null) break;
            return action.payload.id === state.id ? {...state, poopsNumber: action.payload.value, poopWasPut: false}: state;


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