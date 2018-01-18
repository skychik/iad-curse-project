import RestClient from "another-rest-client";

let api = new RestClient('http://localhost:8080');

function getContent(page) {
    // rest({method: 'GET', path: 'localhost:8080/api/news'}).done(response => {
    //     this.setState({news: response.entity._embedded.news});
    // });
    if (page === 'pile')return api.res('api').res('news').get();
    return api.res('api').res(page).get();
}

export function restApiReducer (state = {}, action) {
    switch (action.type) {
        case 'PAGE_CHANGED':
            switch (action.payload) {
                case 'pile':
                case 'events':
                case 'pageNotFound':
                case 'loops':
                case 'profile':
                    return getContent(action.payload);
                default:
                    return null;
            }
        case 'NEWS_RECEIVED':
            return action.payload;
        default:
            return state;
    }
}