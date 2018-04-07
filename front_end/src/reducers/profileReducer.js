export function profileReducer(state = {}, action) {
    switch (action.type) {
        case "PROFILE_RECEIVED":
            return action.payload;
        default:
            return state;
    }
}