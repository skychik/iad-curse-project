export function feedReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_FEED_PENDING": break;
        case "FETCH_FEED_FULFILLED":
            return action.payload;
        case "PUT_NEWS_LOOP_FULFILLED":
            if (state == null) break;
            return state.map((news) =>
                action.payload.id === news.id ? {...news, loopsNumber: action.payload.value, loopWasPut: action.payload.succeed}: news);
        case "PUT_NEWS_POOP_FULFILLED":
            if (state == null) break;
            return state.map((news) =>
                action.payload.id === news.id ? {...news, poopsNumber: action.payload.value, poopWasPut: action.payload.succeed}: news);
        case "REMOVE_NEWS_LOOP_FULFILLED":
            if (state == null) break;
            return state.map((news) =>
                action.payload.id === news.id ? {...news, loopsNumber: action.payload.value, loopWasPut: !action.payload.succeed}: news);
        case "REMOVE_NEWS_POOP_FULFILLED":
            if (state == null) break;
            return state.map((news) =>
                action.payload.id === news.id ? {...news, poopsNumber: action.payload.value, poopWasPut: !action.payload.succeed}: news);
        case "FETCH_FEED_REJECTED":
        case "PUT_NEWS_LOOP_REJECTED":
        case "PUT_NEWS_POOP_REJECTED":
        case "REMOVE_NEWS_LOOP_REJECTED":
        case "REMOVE_NEWS_POOP_REJECTED":
            console.log(action.payload);
            break;
        default:
            return state;
    }
    return state;
}