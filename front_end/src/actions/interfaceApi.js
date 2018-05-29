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