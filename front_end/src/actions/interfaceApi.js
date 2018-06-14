export function showAddComment(commentingComm) {
    return {
        type: 'SHOW_ADD_COMMENT',
        payload: commentingComm,
    }
}

export function hideAddComment() {
    return {
        type: 'HIDE_ADD_COMMENT',
        payload: null,
    }
}

export function setNewCommentContent(content) {
    return {
        type: 'SET_NEW_COMMENT_CONTENT',
        payload: content,
    }
}

export function setCourseBackground(isCourse) {
    return {
        type: 'COURSE_BACKGROUND',
        payload: isCourse,
    }
}

export function dismissError(id) {
    return {
        type: 'DISMISS_ERROR',
        payload: id,
    }
}

export function showChangeSetting(type, header, previousContent) {
    return {
        type: 'SHOW_CHANGE_SETTING',
        payload: {
            type: type,
            previousContent: previousContent,
            header: header,
        },
    }
}

export function hideChangeSetting() {
    return {
        type: 'HIDE_CHANGE_SETTING',
    }
}

export function startValidation(data) {
    return {
        type: 'START_VALIDATION',
        payload: data,
    }
}

export function validateContent(data) {
    return {
        type: 'VALIDATE_CONTENT',
        payload: data,
    }
}