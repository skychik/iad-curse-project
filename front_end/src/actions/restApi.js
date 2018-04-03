export function setFeed(news) {
    // console.log('---receive:---');
    // console.log(news);
    // console.log('---receive---');
    return {
        type: 'NEWS_RECEIVED',
        payload: news,
    }
}
