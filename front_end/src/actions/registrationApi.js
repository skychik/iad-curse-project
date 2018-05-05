export function initValidation() {
    return {
        type: 'VALIDATION_INIT',
        payload: null,
    }
}

export function startValidation(data) {
    return {
        type: 'START_VALIDATION',
        payload: data,
    }
}

export function setValidation(data) {
    return {
        type: 'VALIDATE',
        payload: data,
    }
}