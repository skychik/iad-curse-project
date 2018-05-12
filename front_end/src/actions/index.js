// Actually these are action creators, not actions

import * as restApi from "./restApi";
import * as sessionApi from "./sessionApi";
import * as signForm from "./signFormApi";

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