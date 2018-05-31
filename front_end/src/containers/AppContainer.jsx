import React from 'react';
import FeedContainer from './FeedContainer';
import NewsContainer from './NewsContainer';
import LoopsContainer from "./LoopsContainer";
import CoursesContainer from "./CoursesContainer";
import EventsContainer from "./EventsContainer";
import ProfileContainer from "./ProfileContainer";
import PageNotFound from "../components/PageNotFound";
import { Switch, Route } from 'react-router';
import { Redirect } from "react-router-dom";
import Grid  from 'react-bootstrap/lib/Grid';
import Nav from 'react-bootstrap/lib/Nav';
import Navbar from 'react-bootstrap/lib/Navbar';
import NavItem  from 'react-bootstrap/lib/NavItem';
import cookie from 'react-cookies';
import CreateContainer from "./CreateContainer";
import {Glyphicon, OverlayTrigger, Tooltip} from "react-bootstrap";


class AppContainer extends React.Component {
    render() {
        const createTip = (<Tooltip id="tooltip_username">Create!</Tooltip>);

        return (
            <Grid className="AppContainer">
                <Navbar>
                    <Navbar.Header>
                        <div style={{marginLeft:15}}>
                            <Navbar.Brand>
                                {/*<Image src={LogoImg} alt="brand" rounded />*/}<span>Make loop not poop</span>
                            </Navbar.Brand>
                        </div>
                        <Navbar.Toggle />
                    </Navbar.Header>
                    <Navbar.Collapse>
                        <Nav>
                            <NavItem eventKey={1} href="/feed">Feed</NavItem>
                            <NavItem eventKey={2} href="/events">Events</NavItem>
                            <NavItem eventKey={3} href="/courses">Courses</NavItem>
                            <NavItem eventKey={4} href="/loops">Loops</NavItem>
                            <NavItem eventKey={5} href="/create">
                                <div style={{height: "21px"}}>
                                    <OverlayTrigger placement="bottom" overlay={createTip}>
                                        <a href="/create" className="header-create-button">
                                            <Glyphicon glyph="pencil" style={{color: "black", margin: "0px 0px 0px 33px"}}/>
                                        </a>
                                    </OverlayTrigger>
                                </div>
                            </NavItem>
                        </Nav>
                        <Nav style={{marginRight: 0}} pullRight>
                            <NavItem eventKey={6} href={"/login"}>
                                <div className="header-logout">log out</div>
                            </NavItem>
                            <NavItem eventKey={7} href={"/id/" + cookie.load("userId")} style={{paddingRight:15}}>
                                <div>Profile</div>
                            </NavItem>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
                <Switch>
                    <Route exact path='/feed' component={FeedContainer} />
                    <Route path='/create' component={CreateContainer} />
                    <Route path='/events' component={EventsContainer} />
                    <Route path='/courses' component={CoursesContainer} />
                    <Route path='/loops' component={LoopsContainer} />
                    <Route path='/id/:number' component={ProfileContainer} />
                    <Route path='/news/:number' component={NewsContainer} />
                    <Route path='/page_not_found' component={PageNotFound}/>
                    <Route render={() => <Redirect to='/page_not_found' /> } />
                </Switch>
                <div className="AppContainer-footer">

                </div>
            </Grid>
        );
    }
}

export default AppContainer;
