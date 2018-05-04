export function sessionReducer(state = {}, action) {
    switch (action.type) {
        case "SET_USERID":
            return action.payload;
        default:
            return state;
    }
}
