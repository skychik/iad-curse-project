export function initSignForm() {
    return {
        type: 'SIGN_FORM_INIT',
        payload: null,
    }
}

export function initValidation() {
    return {
        type: 'INIT_SIGN_FORM_VALIDATION',
        payload: null,
    }
}

export function setUserAttribute(data) {
    return {
        type: 'SET_USER_ATTRIBUTE',
        payload: data,
    }
}

export function setRegistrationSuccess(data) {
    return {
        type: 'REGISTRATION_SUCCESS',
        payload: data,
    }
}

export function setSigninSuccess(data) {
    return {
        type: 'SIGNIN_SUCCESS',
        payload: data,
    }
}