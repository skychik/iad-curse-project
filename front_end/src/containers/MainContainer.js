import React from 'react';

class MainContainer extends React.Component {
    render() {
        return (
            <div className="MainContainer">
                {this.props.children}
            </div>
        );
    }
}

export default MainContainer;
