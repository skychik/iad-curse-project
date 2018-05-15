import RestClient from "another-rest-client";
import cookie from "react-cookies";

export function commentsPLoopReducer (state = {}, action) {
    switch (action.type) {
        case 'COMMENTS_LOOP':
            let api1 = new RestClient('http://localhost:8080'); // TODO: make static
            const promise1 = api1.res("comments").res(action.payload).res("loop").get({userId: cookie.load("userId")});
            promise1.then((response) => {
                return JSON.parse(JSON.stringify(response));
            });
            break;
        case 'COMMENTS_POOP':
            let api2 = new RestClient('http://localhost:8080'); // TODO: make static
            const promise2 = api2.res("comments").res(action.payload).res("poop").get({userId: cookie.load("userId")});
            promise2.then((response) => {
                return JSON.parse(JSON.stringify(response));
            });
            break;
        default:
            return state;
    }
}