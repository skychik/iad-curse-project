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