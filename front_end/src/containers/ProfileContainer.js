import React  from 'react';

class Profile extends React.Component {
    render() {
        return (
            <div className="Profile">
                <h2>{this.props.content}</h2>
            </div>
        )
    }
}

export default Profile;