export function mySessionReducer(state = {}, action) {
    switch (action.type) {
        case "SET_SESSION":
            return action.payload;
        default:
            return state;
    }
}
// TODO: make normal session