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
