import {connectedRouterRedirect} from "redux-auth-wrapper/history4/redirect";
import cookie from 'react-cookies'

export const userIsAuthenticated = connectedRouterRedirect({
    // The url to redirect user to if they fail
    redirectPath: '/login',
    // If selector is true, wrapper will not redirect
    // For example let's check that state contains user data
    authenticatedSelector: () => ((cookie.load("userId") !== undefined) && (cookie.load("userId") !== "") && (cookie.load("userId") !== null)),
    // A nice display name for this check
    wrapperDisplayName: 'UserIsAuthenticated'
});

export const userIsNotAuthenticated = connectedRouterRedirect({
    // The url to redirect user to if they fail
    redirectPath: '/feed',
    // If selector is true, wrapper will not redirect
    // For example let's check that state contains user data
    authenticatedSelector: () => ((cookie.load("userId") !== undefined) && (cookie.load("userId") !== "") && (cookie.load("userId") !== null)),
    // A nice display name for this check
    wrapperDisplayName: 'UserIsNotAuthenticated'
});