export function profileReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_PROFILE_FULFILLED":
            return action.payload;
        default:
            return state;
    }
}