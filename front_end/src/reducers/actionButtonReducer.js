export function actionButtonReducer(state = {}, action) {
    switch (action.type) {
        case "PUT_NEWS_LOOP_PENDING":
        case "PUT_NEWS_POOP_PENDING":
        case "REMOVE_NEWS_LOOP_PENDING":
        case "REMOVE_NEWS_POOP_PENDING":
        case "PUT_COMMENT_LOOP_PENDING":
        case "PUT_COMMENT_POOP_PENDING":
        case "REMOVE_COMMENT_LOOP_PENDING":
        case "REMOVE_COMMENT_POOP_PENDING":
        case "PUT_COURSE_TASKS_LOOP_PENDING":
        case "PUT_COURSE_TASKS_POOP_PENDING":
        case "REMOVE_COURSE_TASKS_LOOP_PENDING":
        case "REMOVE_COURSE_TASKS_POOP_PENDING":
        case "COMPLETE_COURSE_TASK_PENDING":
        case "UNDO_COURSE_TASK_PENDING":
            return {...state, pending: true};
        case "PUT_NEWS_LOOP_FULFILLED":
        case "PUT_NEWS_POOP_FULFILLED":
        case "REMOVE_NEWS_LOOP_FULFILLED":
        case "REMOVE_NEWS_POOP_FULFILLED":
        case "PUT_COMMENT_LOOP_FULFILLED":
        case "PUT_COMMENT_POOP_FULFILLED":
        case "REMOVE_COMMENT_LOOP_FULFILLED":
        case "REMOVE_COMMENT_POOP_FULFILLED":
        case "PUT_COURSE_TASK_LOOP_FULFILLED":
        case "PUT_COURSE_TASK_POOP_FULFILLED":
        case "REMOVE_COURSE_TASK_LOOP_FULFILLED":
        case "REMOVE_COURSE_TASK_POOP_FULFILLED":
        case "COMPLETE_COURSE_TASK_FULFILLED":
        case "UNDO_COURSE_TASK_FULFILLED":
        case "PUT_NEWS_LOOP_REJECTED":
        case "PUT_NEWS_POOP_REJECTED":
        case "REMOVE_NEWS_LOOP_REJECTED":
        case "REMOVE_NEWS_POOP_REJECTED":
        case "PUT_COMMENT_LOOP_REJECTED":
        case "PUT_COMMENT_POOP_REJECTED":
        case "REMOVE_COMMENT_LOOP_REJECTED":
        case "REMOVE_COMMENT_POOP_REJECTED":
        case "PUT_COURSE_TASK_LOOP_REJECTED":
        case "PUT_COURSE_TASK_POOP_REJECTED":
        case "REMOVE_COURSE_TASK_LOOP_REJECTED":
        case "REMOVE_COURSE_TASK_POOP_REJECTED":
        case "COMPLETE_COURSE_TASK_REJECTED":
        case "UNDO_COURSE_TASK_REJECTED":
            return {...state, pending: false}
    }
    return state;
}