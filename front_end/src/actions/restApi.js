export function setFeed(data) {
    // console.log('---receive:---');
    // console.log(news);
    // console.log('---receive---');
    return {
        type: 'FEED_RECEIVED',
        payload: data,
    }
}

export function setNews(data) {
    return {
        type: 'NEWS_RECEIVED',
        payload: data,
    }
}

export function setProfile(data) {
    return {
        type: 'PROFILE_RECEIVED',
        payload: data,
    }
}

export function setComments(data) {
    return {
        type: 'COMMENTS_RECEIVED',
        payload: data,
    }
}

export function putLoopOnNewsId(id) {
    return {
        type: 'NEWS_LOOP',
        payload: id,
    }
}

export function putPoopOnNewsId(id) {
    return {
        type: 'NEWS_POOP',
        payload: id,
    }
}

export function putLoopOnCommentId(id) {
    return {
        type: 'COMMENTS_LOOP',
        payload: id,
    }
}

export function putPoopOnCommentId(id) {
    return {
        type: 'COMMENTS_POOP',
        payload: id,
    }
}

