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
import * as sessionApi from "./sessionApi";
import * as signForm from "./signFormApi";
import * as makingNewsApi from "./newsMakerApi";


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
export const setUserId = sessionApi.setUserId;
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

//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  --------------------- MOVE COOKIE and SESSION LOGIC TO SERVER SIDE ---------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ---- SET DELETION TIMER ----  ----------------------------
//  ----------------------------  --- SET USERID AND TOKEN ---  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------