export function courseBackgroundReducer(state = {}, action) {
    if (action.type === "COURSE_BACKGROUND") {
        return action.payload;
    }
    return state;
}