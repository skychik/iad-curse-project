import React from 'react';

class EventsContainer extends React.Component {
    render() {
        return (
            <div className="EventsContainer">
                <h2>{this.props.content}</h2>
            </div>
        )
    }
}

export default EventsContainer;