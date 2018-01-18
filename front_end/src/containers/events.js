import React from 'react';

class Events extends React.Component {
    render() {
        return (
            <div className="Events">
                <h2>{this.props.content}</h2>
            </div>
        )
    }
}

export default Events;