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
export const setNewsListByUserId = restApi.setNewsListByUserId;
export const setCoursesByUserId = restApi.setCoursesByUserId;
export const setNews = restApi.setNews;
export const setComments = restApi.setComments;
export const startValidation = interfaceApi.startValidation;
export const setUserAttribute = signForm.setUserAttribute;
export const initSignForm = signForm.initSignForm;
export const initValidation = signForm.initValidation;
//export const setRegistrationSuccess = signForm.setRegistrationSuccess;
export const setSigninSuccess = signForm.setSigninSuccess;
export const setRedactorNewsTitle = makingNewsApi.setRedactorNewsTitle;
export const setRedactorContent = makingNewsApi.setRedactorContent;
export const setRedactorTaskTitle = makingNewsApi.setRedactorTaskTitle;
export const setRedactorIsNewCourse = makingNewsApi.setRedactorIsNewCourse;
export const setCourseTitle = makingNewsApi.setCourseTitle;
export const setCourseType = makingNewsApi.setCourseType;
export const setCourseId = makingNewsApi.setCourseId;
export const doSignin = restApi.doSignin;
export const putLoopOnNewsId = restApi.putLoopOnNewsId;
export const putPoopOnNewsId = restApi.putPoopOnNewsId;
export const removeLoopOnNewsId = restApi.removeLoopOnNewsId;
export const removePoopOnNewsId = restApi.removePoopOnNewsId;
export const putLoopOnCommentId = restApi.putLoopOnCommentId;
export const putPoopOnCommentId = restApi.putPoopOnCommentId;
export const removeLoopOnCommentId = restApi.removeLoopOnCommentId;
export const removePoopOnCommentId = restApi.removePoopOnCommentId;
export const setCourseBackground = interfaceApi.setCourseBackground;
export const setCourseTasks = restApi.setCourseTasks;
export const setCourseTasksByType = restApi.setCourseTasksByType;
export const setCourseTask = restApi.setCourseTask;
export const findCourseTitle = restApi.findCourseTitle;
export const addNewCourseTask = restApi.addNewCourseTask;
export const initCourseWithTask = restApi.initCourseWithTask;
export const completeCourseTask = restApi.completeCourseTask;
export const undoCourseTask = restApi.undoCourseTask;
export const setCourses = restApi.setCourses;
export const setCourseTypes = restApi.setCourseTypes;
export const putLoopOnTaskId = restApi.putLoopOnTaskId;
export const putPoopOnTaskId = restApi.putPoopOnTaskId;
export const removeLoopOnTaskId = restApi.removeLoopOnTaskId;
export const removePoopOnTaskId = restApi.removePoopOnTaskId;
export const postNews = restApi.postNews;
export const postCourseTask = restApi.postCourseTask;
export const postInitCourseWithTask = restApi.postInitCourseWithTask;
export const subscribeCourseId = restApi.subscribeCourseId;
export const unsubscribeCourseId = restApi.unsubscribeCourseId;
export const followUserId = restApi.followUserId;
export const unfollowUserId = restApi.unfollowUserId;
export const doesUsernameExist = restApi.doesUsernameExist;
export const showAddComment = interfaceApi.showAddComment;
export const hideAddComment = interfaceApi.hideAddComment;
export const addNewComment = restApi.addNewComment;
export const setNewCommentContent = interfaceApi.setNewCommentContent;
export const dismissError = interfaceApi.dismissError;
export const showChangeSetting = interfaceApi.showChangeSetting;
export const hideChangeSetting = interfaceApi.hideChangeSetting;
export const validateContent = interfaceApi.validateContent;
export const changeProfileInfo = restApi.changeProfileInfo;
export const doRegister = restApi.doRegister;

//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  --------------------- MOVE COOKIE and SESSION LOGIC TO SERVER SIDE ---------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ---- SET DELETION TIMER ----  ----------------------------
//  ----------------------------  --- SET USERID AND TOKEN ---  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------
//  ----------------------------  ----------------------------  ----------------------------