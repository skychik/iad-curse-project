import validator from "validator";

export function registrationReducer(state = {}, action) {
    switch (action.type) {
        case "VALIDATION_INIT":
            return {
                firstName: null,
                surname: null,
                sex: null,
                email: null,
                login: null,
                password: null,
                confirmation: null,
                validation: {
                    firstName: null,
                    surname: null,
                    sex: null,
                    email: null,
                    login: null,
                    password: null,
                    confirmation: null,
                },
                validationStarted: {
                    firstName: false,
                    surname: false,
                    sex: false,
                    email: false,
                    login: false,
                    password: false,
                    confirmation: false,
                },
            };
        case "START_VALIDATION":
            switch (action.payload) {
                case "firstName":
                    return {...state, validationStarted: {...state.validationStarted, firstName: true} };
                case "surname":
                    return {...state, validationStarted: {...state.validationStarted, surname: true} };
                case "sex":
                    return {...state, validationStarted: {...state.validationStarted, sex: true} };
                case "email":
                    return {...state, validationStarted: {...state.validationStarted, email: true} };
                case "login":
                    return {...state, validationStarted: {...state.validationStarted, login: true} };
                case "password":
                    return {...state, validationStarted: {...state.validationStarted, password: true} };
                case "confirmation":
                    return {...state, validationStarted: {...state.validationStarted, confirmation: true} };
            }
            return action.payload;
        case "VALIDATE":
            switch (action.payload.name) {
                case "firstName":
                    return {...state, firstName: action.payload.value,
                        validation: {...state.validation, firstName: validator.isAlphanumeric(action.payload.value) ? "success" : "error"} };
                case "surname":
                    return {...state, surname: action.payload.value,
                        validation: {...state.validation, surname: validator.isAlphanumeric(action.payload.value) ? "success" : "error"} };
                case "sex":
                    return {...state, sex: action.payload.value,
                        validation: {...state.validation, sex: action.payload.value} };
                case "email":
                    return {...state, email: action.payload.value,
                        validation: {...state.validation, email: validator.isEmail(action.payload.value) ? "success" : "error"} };
                case "login":
                    return {...state, login: action.payload.value,
                        validation: {...state.validation, login: validator.isAlphanumeric(action.payload.value) ? "success" : "error"} };
                case "password":
                    return {...state, password: action.payload.value,
                        validation: {...state.validation, password: validator.isAlphanumeric(action.payload.value) ? "success" : "error"} };
                case "confirmation":
                    return {...state, confirmation: action.payload.value,
                        validation: {...state.validation, confirmation: (action.payload.value === state.password ? "success" : "error")} };
            }
            return action.payload;
        default:
            return state;
    }
}