// Actually these are action creators, not actions

import * as restApi from "./restApi";
import * as sessionApi from "./sessionApi";
import * as registrationApi from "./registrationApi";

export const setFeed = restApi.setFeed;
export const setProfile = restApi.setProfile;
export const setNews = restApi.setNews;
export const setComments = restApi.setComments;
export const setUserId = sessionApi.setUserId;
export const startValidation = registrationApi.startValidation;
export const setValidation = registrationApi.setValidation;
export const initValidation = registrationApi.initValidation;
