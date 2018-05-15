export function newsMakerReducer(state = {}, action) {
    switch (action.type) {
        case "MAKING_NEWS_TITLE_CHANGED":
            return {...state, title: action.payload};
        case "MAKING_NEWS_CONTENT_CHANGED":
            return {...state, content: action.payload};
        case "POSTING_NEWS_ANSWER":
            return {...state, answer: action.payload};
        default:
            return state;
    }
}