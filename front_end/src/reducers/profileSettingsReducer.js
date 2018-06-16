import validator from "validator";

export function profileSettingsReducer(state = {}, action) {
    switch (action.type) {
        case "SHOW_CHANGE_SETTING":
            return {...state, isModalShown: true, type: action.payload.type, previousContent: action.payload.previousContent,
                header: action.payload.header};
        case "HIDE_CHANGE_SETTING":
            return {
                isModalShown: false,
                type: "",
                content: null,
                confirmation: null,
                doesExist: null,
                previousContent: null,
                header: null,
                validationStarted: false,
                isValid: null,
            };
        case "START_VALIDATION":
            return {...state, validationStarted: true};
        case "DOES_USERNAME_EXIST_FULFILLED":
            return {...state, doesExist: action.payload};
        case "VALIDATE_CONTENT": {
            console.log("payload:");
            console.log(action.payload);
            switch (action.payload.name) {
                case "firstName":
                case "surname":
                // case "patronymic":
                    return {...state,
                        content: action.payload.value,
                        isValid: validator.isAlpha(action.payload.value)
                    };
                case "sex":
                    return {...state,
                        content: action.payload.value,
                        isValid: action.payload.value != null
                    };
                case "email":
                    return {...state,
                        content: action.payload.value,
                        isValid: validator.isEmail(action.payload.value)
                    };
                case "login":
                case "username":
                    return {...state,
                        content: action.payload.value,
                        isValid: validator.isAlphanumeric(action.payload.value) && (action.payload.value.length >= 3)
                    };
                case "password":
                    return {...state,
                        content: action.payload.value,
                        isValid: validator.isAlphanumeric(action.payload.value) && (action.payload.value.length >= 6) &&
                            action.payload.value === state.confirmation,
                    };
                case "confirmation": {
                    return {...state,
                        confirmation: action.payload.value,
                        isValid: validator.isAlphanumeric(action.payload.value) && (action.payload.value.length >= 6) &&
                            action.payload.value === state.password,
                    };
                }
            }
            return state;
        }
    }
    return state;
}