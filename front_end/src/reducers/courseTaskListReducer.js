export function courseTaskListReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_COURSE_TASKS_PENDING": break;
        case "FETCH_COURSE_TASKS_FULFILLED":
            return action.payload;
        case "PUT_COURSE_TASK_LOOP_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, loopsNumber: action.payload.value, loopWasPut: true} : task);
        case "PUT_COURSE_TASK_POOP_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, poopsNumber: action.payload.value, poopWasPut: true} : task);
        case "REMOVE_COURSE_TASK_LOOP_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, loopsNumber: action.payload.value, loopWasPut: false} : task);
        case "REMOVE_COURSE_TASK_POOP_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload.id === task.id ? {...task, poopsNumber: action.payload.value, poopWasPut: false} : task);
        case "COMPLETE_COURSE_TASK_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload === task.id ? {...task, wasCompleted: true} : task);
        case "UNDO_COURSE_TASK_FULFILLED":
            if (state == null) break;
            return state.map((task) =>
                action.payload === task.id ? {...task, wasCompleted: false} : task);
        case "FETCH_COURSE_TASKS_REJECTED":
    }
    return state;
}