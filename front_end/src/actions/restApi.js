import {restApiUrl} from "../options";
import axios from "axios"
import cookie from "react-cookies";
import RestClient from "another-rest-client";


// ------------------------------------------------------ ACTIONS ------------------------------------------------------

export function putLoopOnNewsId(id) {
    return {
        type: 'PUT_NEWS_LOOP',
        payload: api.res("news").res(id.toString()).res("loop").res("put").get({userId: cookie.load("userId")}),
    }
}

export function putPoopOnNewsId(id) {
    return {
        type: 'PUT_NEWS_POOP',
        payload: api.res("news").res(id.toString()).res("poop").res("put").get({userId: cookie.load("userId")}),
    }
}

export function removeLoopOnNewsId(id) {
    return {
        type: 'REMOVE_NEWS_LOOP',
        payload: api.res("news").res(id.toString()).res("loop").res("remove").get({userId: cookie.load("userId")}),
    }
}

export function removePoopOnNewsId(id) {
    return {
        type: 'REMOVE_NEWS_POOP',
        payload: api.res("news").res(id.toString()).res("poop").res("remove").get({userId: cookie.load("userId")}),
    }
}

export function putLoopOnCommentId(id) {
    return {
        type: 'PUT_COMMENT_LOOP',
        payload: api.res("comments").res(id.toString()).res("loop").res("put").get({userId: cookie.load("userId")}),
    }
}

export function putPoopOnCommentId(id) {
    return {
        type: 'PUT_COMMENT_POOP',
        payload: api.res("comments").res(id.toString()).res("poop").res("put").get({userId: cookie.load("userId")}),
    }
}

export function removeLoopOnCommentId(id) {
    return {
        type: 'REMOVE_COMMENT_LOOP',
        payload: api.res("comments").res(id.toString()).res("loop").res("remove").get({userId: cookie.load("userId")}),
    }
}

export function removePoopOnCommentId(id) {
    return {
        type: 'REMOVE_COMMENT_POOP',
        payload: api.res("comments").res(id.toString()).res("poop").res("remove").get({userId: cookie.load("userId")}),
    }
}

// ------------------------------------------------------ SETTERS ------------------------------------------------------

export function setFeed() {
    // console.log('---receive:---');
    // console.log(news);
    // console.log('---receive---');
    return {
        type: 'FETCH_FEED',
        payload: api.res("news").res("for").get({userId: cookie.load("userId")}),
    }
}

export function setNews(number) {
    return {
        type: 'FETCH_NEWS',
        payload: api.res("news").res(number.toString()).get({userId: cookie.load("userId")}),
    }
}

export function setProfile(data) {
    return {
        type: 'PROFILE_RECEIVED',
        payload: data,
    }
}

export function setComments(newsId) {
    return {
        type: 'FETCH_COMMENTS',
        payload: api.res("comments").res("for").get({newsId: newsId,
                                                     userId: cookie.load("userId")}),
    }
}

export function setNewsLoop(id) {
    return {
        type: 'NEWS_LOOP_RECEIVED',
        payload: id,
    }
}

export function setNewsPoop(id) {
    return {
        type: 'NEWS_POOP_RECEIVED',
        payload: id,
    }
}

export function setCommentsLoop(id) {
    return {
        type: 'COMMENTS_LOOP_RECEIVED',
        payload: id,
    }
}

export function setCommentsPoop(id) {
    return {
        type: 'COMMENTS_POOP_RECEIVED',
        payload: id,
    }
}

// ------------------------------------------------------ ....... ------------------------------------------------------

const api = new RestClient('http://localhost:8080');