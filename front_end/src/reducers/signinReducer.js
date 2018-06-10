import cookie from "react-cookies";

export function signinReducer(state = {}, action) {
    switch (action.type) {
        case "SIGNIN_PENDING":
            return {...state, pending: true};
        case "SIGNIN_FULFILLED":
            // console.log("userId=" + cookie.load("userId"));
            if (cookie.load("userId") != null && cookie.load("userId") !== "") {
                //this.props.history.push("/feed");
            } else {
                console.log("something went wrong while logging");
            }
            return {...state, pending: false, success: true};
        case "SIGNIN_REJECTED":
            console.log(action.payload.response);
            return {...state, pending: false, success: false, answer: action.payload.response};
    }
    return state;
}