import {restApiUrl} from "../options";
import axios from "axios"
import cookie from "react-cookies";
import RestClient from "another-rest-client";


// ------------------------------------------------------ ACTIONS ------------------------------------------------------

export function putLoopOnNewsId(id) {
    return {
        type: 'PUT_NEWS_LOOP',
        payload: api.res("news").res(id.toString()).res("loop").res("put").get(),
    }
}

export function putPoopOnNewsId(id) {
    return {
        type: 'PUT_NEWS_POOP',
        payload: api.res("news").res(id.toString()).res("poop").res("put").get(),
    }
}

export function removeLoopOnNewsId(id) {
    return {
        type: 'REMOVE_NEWS_LOOP',
        payload: api.res("news").res(id.toString()).res("loop").res("remove").get(),
    }
}

export function removePoopOnNewsId(id) {
    return {
        type: 'REMOVE_NEWS_POOP',
        payload: api.res("news").res(id.toString()).res("poop").res("remove").get(),
    }
}

export function putLoopOnCommentId(id) {
    return {
        type: 'PUT_COMMENT_LOOP',
        payload: api.res("comments").res(id.toString()).res("loop").res("put").get(),
    }
}

export function putPoopOnCommentId(id) {
    return {
        type: 'PUT_COMMENT_POOP',
        payload: api.res("comments").res(id.toString()).res("poop").res("put").get(),
    }
}

export function removeLoopOnCommentId(id) {
    return {
        type: 'REMOVE_COMMENT_LOOP',
        payload: api.res("comments").res(id.toString()).res("loop").res("remove").get(),
    }
}

export function removePoopOnCommentId(id) {
    return {
        type: 'REMOVE_COMMENT_POOP',
        payload: api.res("comments").res(id.toString()).res("poop").res("remove").get(),
    }
}

export function putLoopOnTaskId(id) {
    return {
        type: 'PUT_COURSE_TASKS_LOOP',
        payload: api.res("comments").res(id.toString()).res("loop").res("put").get(),
    }
}

export function putPoopOnTaskId(id) {
    return {
        type: 'PUT_COURSE_TASKS_POOP',
        payload: api.res("comments").res(id.toString()).res("poop").res("put").get(),
    }
}

export function removeLoopOnTaskId(id) {
    return {
        type: 'REMOVE_COURSE_TASKS_LOOP',
        payload: api.res("comments").res(id.toString()).res("loop").res("remove").get(),
    }
}

export function removePoopOnTaskId(id) {
    return {
        type: 'REMOVE_COURSE_TASKS_POOP',
        payload: api.res("comments").res(id.toString()).res("poop").res("remove").get(),
    }
}


export function addNewComment(comment) {
    return {
        type: 'ADD_COMMENT',
        payload: api.res("comments").res("add").get({newsId: comment.newsId,
            onCommentId: comment.onCommentId, content: comment.content}),
    }
}

export function addNewCourseTask(task) {
    return {
        type: 'ADD_NEW_COURSE_TASK',
        payload: api.res("course").res("task").res("add").get({courseId: task.courseId,
            title: task.title, content: task.content}),
    }
}

export function initCourseWithTask(task) {
    return {
        type: 'INIT_COURSE_WITH_TASK',
        payload: api.res("course").res("task").res("init_course_with_task").get({courseId: task.courseId,
            title: task.title, content: task.content,
            course: {authorId: task.course.authorId, title: task.course.title, type: task.course.type}}),
    }
}

export function completeCourseTask(taskId) {
    return {
        type: 'COMPLETE_COURSE_TASK',
        payload: api.res("course").res("task").res(taskId.toString()).res("complete").get(),
    }
}

export function undoCourseTask(taskId) {
    return {
        type: 'UNDO_COURSE_TASK',
        payload: api.res("course").res("task").res(taskId.toString()).res("undo").get(),
    }
}

export function postNews(data) {
    return {
        type: 'POST_NEWS',
        payload: api.res("news").res("post").post(data),
    }
}

export function postCourseTask(data) {
    return {
        type: 'POST_COURSE_TASK',
        payload: api.res("course").res("task").res("add").post(data),
    }
}

export function postInitCourseWithTask(data) {
    return {
        type: 'POST_INIT_COURSE_WITH_TASK',
        payload: api.res("course").res("task").res("init_course_with_task").post(data),
    }
}

// ------------------------------------------------------ SETTERS ------------------------------------------------------

export function setFeed() {
    // console.log('---receive:---');
    // console.log(news);
    // console.log('---receive---');
    return {
        type: 'FETCH_FEED',
        payload: api.res("news").res("for").get(),
    }
}

export function setNews(number) {
    return {
        type: 'FETCH_NEWS',
        payload: api.res("news").res(number.toString()).get(),
    }
}

export function setProfile(number) {
    return {
        type: 'FETCH_PROFILE',
        payload: api.res("user").res(number.toString()).get(),
    }
}

export function setComments(newsId) {
    return {
        type: 'FETCH_COMMENTS',
        payload: api.res("comments").res("for").get({newsId: newsId}),
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

export function setCourseTasks() {
    return {
        type: 'FETCH_CURSE_TASKS',
        payload: api.res("course").res("tasks").res("for").get(),
    }
}

export function setCourseTask(number) {
    return {
        type: 'FETCH_CURSE_TASK',
        payload: api.res("course").res("task").res(number.toString()).get(),
    }
}

export function findCourseTitle(title) {
    return {
        type: 'IS_USER_NEWS_TITLE_EXISTS',
        payload: api.res("course").res("title_exists").get({title: title}),
    }
}

export function setCourses() {
    return {
        type: 'FETCH_USER_COURSES',
        payload: api.res("course").res("list").get(),
    }
}

export function setCourseTypes() {
    return {
        type: 'FETCH_COURSE_TYPES',
        payload: api.res("course").res("types").get(),
    }
}

// ------------------------------------------------------ ....... ------------------------------------------------------

const api = new RestClient('http://46.101.111.25:8080');
api.on('request', function (xhr) {
    xhr.withCredentials = true;
});