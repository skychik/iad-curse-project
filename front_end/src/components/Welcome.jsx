import React from 'react';
import {Button, Grid} from "react-bootstrap";

class Welcome extends React.Component {
    render() {
        return (
            <Grid className="Welcome">
                <div className="welcome-case">
                    <h1 className="welcome-title">Welcome to Muzloop</h1>
                    <p>
                        <Button bsStyle="primary" href="/login">Sing in</Button>
                        &emsp;
                        <Button bsStyle="link" href="/register">Register</Button>
                    </p>
                </div>
            </Grid>
        )
    }
}

export default Welcome;