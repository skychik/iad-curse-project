// Actually these are action creators, not actions

import * as pageActions from "./pageActions";
import * as restApi from "./restApi";

export const changePage = pageActions.changePage;
export const receiveNews = restApi.receiveNews;