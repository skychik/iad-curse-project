export function setRedactorNewsTitle(title) {
    return {
        type: 'REDACTOR_NEWS_TITLE_CHANGED',
        payload: title,
    }
}

export function setCourseTitle(title) {
    return {
        type: 'REDACTOR_NEWS_COURSE_TITLE_CHANGED',
        payload: title,
    }
}

export function setCourseType(type) {
    return {
        type: 'REDACTOR_NEWS_COURSE_TYPE_CHANGED',
        payload: type,
    }
}

export function setCourseId(id) {
    return {
        type: 'REDACTOR_NEWS_CHOOSE_COURSE_ID',
        payload: id,
    }
}

export function setRedactorContent(data) {
    return {
        type: 'REDACTOR_NEWS_CONTENT_CHANGED',
        payload: data,
    }
}

export function setRedactorTaskTitle(data) {
    return {
        type: 'REDACTOR_TASK_TITLE_CHANGED',
        payload: data,
    }
}

export function setRedactorIsNewCourse(isNew) {
    return {
        type: 'SET_IS_NEW_COURSE',
        payload: isNew,
    }
}