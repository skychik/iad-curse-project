import validator from "validator";
import RestClient from "another-rest-client";

export function signinFormReducer(state = {}, action) {
    switch (action.type) {
        case "SIGN_FORM_INIT":
            return {
                firstName: null,
                surname: null,
                patronymic: null,
                sex: null,
                email: null,
                login: null,
                password: null,
                confirmation: null,
                registrationSubmitEnabled: false,
                signinSubmitEnabled: false,
                formChanged: true,
                validation: {
                    firstName: null,
                    surname: null,
                    sex: null,
                    email: null,
                    loginCorrect: null,
                    loginExists: null,
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
        case "INIT_SIGN_FORM_VALIDATION":
            return {...state,
                registrationSubmitEnabled: false,
                signinSubmitEnabled: false,
                formChanged: false,
                validation: {
                    firstName: false,
                    surname: false,
                    sex: false,
                    email: false,
                    loginCorrect: false,
                    loginExists: false,
                    password: false,
                    confirmation: false,
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
                // case "patronymic":
                //     return {...state, validationStarted: {...state.validationStarted, patronymic: true} };
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
                default:
                    return action.payload;
            }
        case "SET_USER_ATTRIBUTE":
            switch (action.payload.name) {
                case "firstName": {
                    const transitionalState = {...state,
                        firstName: action.payload.value,
                        validation: {...state.validation, firstName: validator.isAlpha(action.payload.value)} };
                    return {...transitionalState,
                        registrationSubmitEnabled: doesRegistrationSubmitEnabled(transitionalState),
                        signinSubmitEnabled: doesSigninRegistrationEnabled(transitionalState),
                        formChanged: true,};}
                case "surname": {
                    const transitionalState = {...state,
                        surname: action.payload.value,
                        validation: {...state.validation, surname: validator.isAlpha(action.payload.value)} };
                    return {...transitionalState,
                        registrationSubmitEnabled: doesRegistrationSubmitEnabled(transitionalState),
                        signinSubmitEnabled: doesSigninRegistrationEnabled(transitionalState),
                        formChanged: true,};}
                // case "patronymic": {
                //     const transitionalState = {...state,
                //         patronymic: putAction.payload.value,
                //         validation: {...state.validation, patronymic: validator.isAlpha(putAction.payload.value) ? "success" : "error"} };
                //     return {...transitionalState, submitEnabled: doesRegistrationSubmitEnabled(transitionalState)};}
                case "sex": {
                    const transitionalState = {...state,
                        sex: action.payload.value,
                        validation: {...state.validation, sex: action.payload.value != null} };
                    return {...transitionalState,
                        registrationSubmitEnabled: doesRegistrationSubmitEnabled(transitionalState),
                        signinSubmitEnabled: doesSigninRegistrationEnabled(transitionalState),
                        formChanged: true,};}
                case "email": {
                    const transitionalState = {...state,
                        email: action.payload.value,
                        validation: {...state.validation, email: validator.isEmail(action.payload.value)} };
                    return {...transitionalState,
                        registrationSubmitEnabled: doesRegistrationSubmitEnabled(transitionalState),
                        signinSubmitEnabled: doesSigninRegistrationEnabled(transitionalState),
                        formChanged: true,};}
                case "loginCorrect": { // save login, but null the validation
                    const transitionalState = {...state,
                        login: action.payload.value,
                        validation: {...state.validation,
                            loginCorrect: (validator.isAlphanumeric(action.payload.value) && (action.payload.value.length >= 3)),
                        },
                    };
                    return {...transitionalState,
                        registrationSubmitEnabled: doesRegistrationSubmitEnabled(transitionalState),
                        signinSubmitEnabled: doesSigninRegistrationEnabled(transitionalState),
                        formChanged: true,};}
                case "loginExists":
                    console.log("!!!isAlphaNum=" + validator.isAlphanumeric(state.login));
                    console.log("!!!>=3 =" + (state.login >= 3));
                    console.log("!!!loginExists=" + action.payload.value);
                    const transitionalState = {...state,
                        validation: {...state.validation,
                            loginExists: action.payload.value
                        },
                    };
                    console.log("transState=");
                    console.log(transitionalState);
                    return {...transitionalState,
                        registrationSubmitEnabled: doesRegistrationSubmitEnabled(transitionalState),
                        signinSubmitEnabled: doesSigninRegistrationEnabled(transitionalState),
                        formChanged: true,};
                case "password": {
                    const transitionalState = {...state,
                        password: action.payload.value,
                        validation: {...state.validation, password: (validator.isAlphanumeric(action.payload.value) && (action.payload.value.length >= 6))} };
                    return {...transitionalState,
                        registrationSubmitEnabled: doesRegistrationSubmitEnabled(transitionalState),
                        signinSubmitEnabled: doesSigninRegistrationEnabled(transitionalState),
                        formChanged: true,};}
                case "confirmation": {
                    const transitionalState = {...state,
                        confirmation: action.payload.value,
                        validation: {...state.validation, confirmation: (action.payload.value === state.password)} };
                    return {...transitionalState,
                        registrationSubmitEnabled: doesRegistrationSubmitEnabled(transitionalState),
                        signinSubmitEnabled: doesSigninRegistrationEnabled(transitionalState),
                        formChanged: true,};}
                default:
                    return state;
            }
        case "REGISTRATION_SUCCESS":
            alert("REGISTRATION_SUCCESS=" + action.payload.value);
            break;
        case "SIGNIN_SUCCESS":
            alert("SIGNIN_SUCCESS=" + action.payload.value);
            break;
        default:
            return state;
    }
}

function doesSigninRegistrationEnabled(state) {
    if (!state.validation.loginCorrect) {
        return false;
    }
    if (!state.validation.password) {
        return false;
    }
    return true;
}

function doesRegistrationSubmitEnabled(state) {
    if (!state.validation.firstName) {
        // console.log("firstName");
        return false;
    }
    if (!state.validation.surname) {
        // console.log("surname");
        return false;
    }
    // if (!state.validation.patronymic) {
    //     // console.log("patronymic");
    //     return false;
    // }

    //if (!state.validation.sex") {
    // return false;
// }
    if (!state.validation.email) {
        // console.log("email");
        return false;
    }
    if (!state.validation.loginCorrect) {
        // console.log("login");
        return false;
    }
    if (!state.validation.password) {
        // console.log("password");
        return false;
    }
    if (!state.validation.confirmation) {
        // console.log("confirmation");
        return false;
    }
    // console.log("none");
    return true;
}

// function setUserInfo(obj) {
//     state.setUserInfo(obj);
// }

// function doesLoginExist(login) {
//     let api = new RestClient('http://localhost:8080');
//     const promise = api.res("user").res("doesExist").get({login: login});
//     promise.then((response) => {
//         console.log("doesLoginExist=" + response);
//         this.setUserInfo({
//             name: "loginExistence",
//             value: response,
//         });
//     });
// }