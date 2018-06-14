export function courseTypesReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_COURSE_TYPES_PENDING":
            return {...state, correct: null};
        case "FETCH_COURSE_TYPES_FULFILLED":
            return {...state, types: action.payload, correct: true};
        case "FETCH_COURSE_TYPES_REJECTED":
            return {...state, answer: action.payload, correct: false};
    }
    return state;
}