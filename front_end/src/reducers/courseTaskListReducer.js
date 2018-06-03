export function courseTaskListReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_COURSE_TASKS_PENDING": break;
        case "FETCH_COURSE_TASKS_FULFILLED":
            return action.payload;
        case "PUT_COURSE_TASKS_LOOP_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, loopsNumber: action.payload.value, loopWasPut: action.payload.succeed} : task);
        case "PUT_COURSE_TASKS_POOP_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, poopsNumber: action.payload.value, poopWasPut: action.payload.succeed} : task);
        case "COMPLETE_COURSE_TASK_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, wasComplete: action.payload.succeed} : task);
        case "REMOVE_COURSE_TASKS_LOOP_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, loopsNumber: action.payload.value, loopWasPut: !action.payload.succeed} : task);
        case "REMOVE_COURSE_TASKS_POOP_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, poopsNumber: action.payload.value, poopWasPut: !action.payload.succeed} : task);
        case "UNDO_COURSE_TASK_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, wasComplete: action.payload.succeed} : task);
        case "FETCH_COURSE_TASKS_REJECTED":
        case "PUT_COURSE_TASKS_LOOP_REJECTED":
        case "PUT_COURSE_TASKS_POOP_REJECTED":
        case "COMPLETE_COURSE_TASK_REJECTED":
        case "REMOVE_COURSE_TASKS_LOOP_REJECTED":
        case "REMOVE_COURSE_TASKS_POOP_REJECTED":
        case "UNDO_COURSE_TASK_REJECTED":
            console.log(action.payload);
            break;
        default:
            return state;
    }
    return state;
}