// import RestClient from "another-rest-client";
// import cookie from "react-cookies";
//
// export function PLoopReducer (state = {}, putAction) {
//     switch (putAction.type) {
//         case 'PUT_NEWS_LOOP':
//             let api1 = new RestClient('http://localhost:8080'); // TODO: make static
//             const promise1 = api1.res("news").res(putAction.payload.toString()).res("loop").get({userId: cookie.load("userId")});
//             promise1.then((response) => {
//                 return JSON.parse(JSON.stringify(response));
//             });
//             break;
//         case 'PUT_NEWS_POOP':
//             let api2 = new RestClient('http://localhost:8080'); // TODO: make static
//             const promise2 = api2.res("news").res(putAction.payload.toString()).res("poop").get({userId: cookie.load("userId")});
//             promise2.then((response) => {
//                 return JSON.parse(JSON.stringify(response));
//             });
//             break;
//         case 'PUT_COMMENTS_LOOP':
//             let api3 = new RestClient('http://localhost:8080'); // TODO: make static
//             const promise3 = api3.res("comments").res(putAction.payload.toString()).res("loop").get({userId: cookie.load("userId")});
//             promise3.then((response) => {
//                 return JSON.parse(JSON.stringify(response));
//             });
//             break;
//         case 'PUT_COMMENTS_POOP':
//             let api4 = new RestClient('http://localhost:8080'); // TODO: make static
//             const promise4 = api4.res("comments").res(putAction.payload.toString()).res("poop").get({userId: cookie.load("userId")});
//             promise4.then((response) => {
//                 return JSON.parse(JSON.stringify(response));
//             });
//             break;
//         default:
//             return state;
//     }
// }