export function commentsReducer(state = {}, action) {
    switch (action.type) {
        case "COMMENTS_RECEIVED":
            return action.payload;
        default:
            return state;
    }
}
