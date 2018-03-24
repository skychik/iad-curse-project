import React from 'react';
import * as actionCreators from "../actions";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

class MainContainer extends React.Component {
    //newsRendering = null;

    render() {
        // let promise = Promise.defer();
        // this.newsRendering = this.asyncRenderNews(null);
        // asyncGetNews()
        //     .then((news) => asyncRenderNews(news))
        //
        //     .catch((error) => console.log("Error occured: " + error.message));

        return (
            <div className="MainContainer">
                {this.props.children}
            </div>
        );
    }
}

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(undefined, mapDispatchToProps)(MainContainer);
