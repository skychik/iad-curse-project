export function setNewsMakerContent(data) {
    return {
        type: 'MAKING_NEWS_CONTENT_CHANGED',
        payload: data,
    }
}

export function setNewsMakerTitle(data) {
    return {
        type: 'MAKING_NEWS_TITLE_CHANGED',
        payload: data,
    }
}

export function setPostingNewsAnswer(data) {
    return {
        type: 'POSTING_NEWS_ANSWER',
        payload: data,
    }
}