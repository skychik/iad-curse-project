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
    // console.log('---receive:---');
    // console.log(news);
    // console.log('---receive---');
    return {
        type: 'NEWS_RECEIVED',
        payload: data,
    }
}

export function setProfile(data) {
    // console.log('---receive:---');
    // console.log(news);
    // console.log('---receive---');
    return {
        type: 'PROFILE_RECEIVED',
        payload: data,
    }
}
