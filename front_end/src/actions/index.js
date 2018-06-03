// Actually these are putAction creators, not actions


//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  --------------------- MOVE COOKIE and SESSION LOGIC TO SERVER SIDE ---------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ---- SET DELETION TIMER ----  ----------------------------
//  ----------------------------  --- SET USERID AND TOKEN ---  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
import * as restApi from "./restApi";
import * as signForm from "./signFormApi";
import * as makingNewsApi from "./newsMakerApi";
import * as interfaceApi from "./interfaceApi";


//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  --------------------- MOVE COOKIE and SESSION LOGIC TO SERVER SIDE ---------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ---- SET DELETION TIMER ----  ----------------------------
//  ----------------------------  --- SET USERID AND TOKEN ---  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
export const setFeed = restApi.setFeed;
export const setProfile = restApi.setProfile;
export const setNews = restApi.setNews;
export const setComments = restApi.setComments;
export const startValidation = signForm.startValidation;
export const setUserAttribute = signForm.setUserAttribute;
export const initSignForm = signForm.initSignForm;
export const initValidation = signForm.initValidation;
export const setRegistrationSuccess = signForm.setRegistrationSuccess;
export const setSigninSuccess = signForm.setSigninSuccess;
export const setNewsMakerContent = makingNewsApi.setNewsMakerContent;
export const setNewsMakerTitle = makingNewsApi.setNewsMakerTitle;
export const setPostingNewsAnswer = makingNewsApi.setPostingNewsAnswer;
export const putLoopOnNewsId = restApi.putLoopOnNewsId;
export const putPoopOnNewsId = restApi.putPoopOnNewsId;
export const removeLoopOnNewsId = restApi.removeLoopOnNewsId;
export const removePoopOnNewsId = restApi.removePoopOnNewsId;
export const putLoopOnCommentId = restApi.putLoopOnCommentId;
export const putPoopOnCommentId = restApi.putPoopOnCommentId;
export const removeLoopOnCommentId = restApi.removeLoopOnCommentId;
export const removePoopOnCommentId = restApi.removePoopOnCommentId;
export const setCourseBackground = interfaceApi.setCourseBackground;
export const setCurseTasks = restApi.setCurseTasks;
export const setCurseTask = restApi.setCurseTask;
export const findCurseTitle = restApi.findCurseTitle;
export const addNewCourseTask = restApi.addNewCourseTask;
export const initCourseWithTask = restApi.initCourseWithTask;
export const completeCourseTask = restApi.completeCourseTask;
export const undoCourseTask = restApi.undoCourseTask;
export const putLoopOnTaskId = restApi.putLoopOnTaskId;
export const putPoopOnTaskId = restApi.putPoopOnTaskId;
export const removeLoopOnTaskId = restApi.removeLoopOnTaskId;
export const removePoopOnTaskId = restApi.removePoopOnTaskId;
export const showAddComment = interfaceApi.showAddComment;
export const hideAddComment = interfaceApi.hideAddComment;
export const addNewComment = restApi.addNewComment;
export const setNewCommentContent = interfaceApi.setNewCommentContent;

//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  --------------------- MOVE COOKIE and SESSION LOGIC TO SERVER SIDE ---------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ---- SET DELETION TIMER ----  ----------------------------
//  ----------------------------  --- SET USERID AND TOKEN ---  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------