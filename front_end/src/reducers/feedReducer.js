export function feedReducer(state = {}, action) {
    switch (action.type) {
        case "FEED_RECEIVED":
            return action.payload;
        default:
            return state;
    }
}