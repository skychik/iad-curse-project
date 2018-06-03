export function courseTaskReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_COURSE_TASK_FULFILLED":
            return action.payload;
        case "FETCH_COURSE_TASK_REJECTED":
            console.log(action.payload);
            break;
        case "PUT_COURSE_TASK_LOOP_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, loopsNumber: action.payload.value, loopWasPut: action.payload.succeed}: state;
        case "PUT_COURSE_TASK_POOP_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, poopsNumber: action.payload.value, poopWasPut: action.payload.succeed}: state;
        case "COMPLETE_COURSE_TASK_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, wasComplete: action.payload.succeed}: state;
        case "REMOVE_COURSE_TASK_LOOP_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, loopsNumber: action.payload.value, loopWasPut: !action.payload.succeed}: state;
        case "REMOVE_COURSE_TASK_POOP_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, poopsNumber: action.payload.value, poopWasPut: !action.payload.succeed}: state;
        case "UNDO_COURSE_TASK_FULFILLED":
            if (state == null) break;
            return action.payload.id === state.id ? {...state, wasComplete: !action.payload.succeed}: state;
        default:
            return state;
    }
    return state;
}