import React from 'react';
//import rest from './rest';
import FeedContainer from './FeedContainer';
import NewsContainer from './NewsContainer';
import LoopsContainer from "./LoopsContainer";
import MentorsContainer from "./MentorsContainer";
import EventsContainer from "./EventsContainer";
import ProfileContainer from "./ProfileContainer";
import PageNotFound from "../components/PageNotFound";
import { Switch, Route } from 'react-router';
import { Redirect } from "react-router-dom";
import LogoImg from '../images/logo.png'
// import client from 'rest'
// import * as actionCreators from "../actions";
// import {bindActionCreators} from "redux";
// import {connect} from "react-redux";
// import reducers from "../reducers"; // TODO: deal with 'connect' decorator
import Grid  from 'react-bootstrap/lib/Grid';
import Nav from 'react-bootstrap/lib/Nav';
import Navbar from 'react-bootstrap/lib/Navbar';
import NavItem  from 'react-bootstrap/lib/NavItem';
import Image from "react-bootstrap/lib/Image";
import cookie from 'react-cookies';


class AppContainer extends React.Component {
    render() {
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
                            <NavItem eventKey={3} href="/mentors">Mentors</NavItem>
                            <NavItem eventKey={4} href="/loops">Loops</NavItem>
                        </Nav>
                        <Nav style={{marginRight: 0}} pullRight>
                            {/*<Navbar.Form>*/}
                                {/*<FormGroup>*/}
                                    {/*<FormControl type="text" placeholder="Search" />*/}
                                {/*</FormGroup>{' '}*/}
                                {/*<Button type="submit">Submit</Button>*/}
                            {/*</Navbar.Form>*/}
                            <NavItem eventKey={5} href={"/login"}>
                                <div className="header-logout">log out</div>
                            </NavItem>
                            <NavItem eventKey={6} href={"/id/" + cookie.load("userId")} style={{paddingRight:15}}>
                                <div>Profile</div>
                            </NavItem>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
                {/*<div className="AppContainer-header">*/}
                    {/*<div className="AppContainer-header_img-frame">*/}
                        {/*<img className="AppContainer-logo" src={LogoImg} />*/}
                        {/*/!*<div className="AppContainer-header_fix" />*!/*/}
                    {/*</div>*/}
                    {/*<Link className="AppContainer-header_buttons" to='/feed'>*/}
                        {/*<div>Feed</div>*/}
                        {/*/!*<div className="AppContainer-header_fix" />*!/*/}
                    {/*</Link>*/}
                    {/*<Link className="AppContainer-header_buttons" to='/events'>*/}
                        {/*<div>Events</div>*/}
                        {/*/!*<div className="AppContainer-header_fix" />*!/*/}
                    {/*</Link>*/}
                    {/*<Link className="AppContainer-header_buttons" to='/mentors'>*/}
                        {/*<div>Mentors</div>*/}
                        {/*/!*<div className="AppContainer-header_fix" />*!/*/}
                    {/*</Link>*/}
                    {/*<Link className="AppContainer-header_buttons" to='/loops'>*/}
                        {/*<div>Loops</div>*/}
                        {/*/!*<div className="AppContainer-header_fix" />*!/*/}
                    {/*</Link>*/}
                    {/*<div className="AppContainer-header_spring" />*/}
                    {/*<Link className="AppContainer-header_buttons right-align-block" to='/profile'>*/}
                        {/*<div>Profile</div>*/}
                        {/*/!*<div className="AppContainer-header_fix" />*!/*/}
                    {/*</Link>*/}
                    {/*/!*<div className="AppContainer-header_fix" />*!/*/}
                {/*</div>*/}
                <Switch>
                    <Route exact path='/feed' component={FeedContainer} />
                    <Route path='/events' component={EventsContainer} />
                    <Route path='/mentors' component={MentorsContainer} />
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


// class NewsList extends Component{
//     render() {
//         var news = this.props.news.map(news =>
//             <News key={news._links.self.href} news={news}/>
//         );
//         return (
//             <table>
//                 <tbody>
//                 <tr>
//                     <th>Id</th>
//                     <th>Id User</th>
//                     <th>xx</th>
//                     <th>Creation Date</th>
//                     <th>Alter Date</th>
//                     <th>Tags</th>
//                 </tr>
//                 {news}
//                 </tbody>
//             </table>
//         )
//     }
// }
//
// class News extends Component{
//     render() {
//         return (
//             <tr>
//                 <td>{this.props.news.id}</td>
//                 <td>{this.props.news.id_user}</td>
//                 <td>{this.props.news.content}</td>
//                 <td>{this.props.news.creation_date}</td>
//                 <td>{this.props.news.alter_date}</td>
//                 <td>{this.props.news.tags}</td>
//             </tr>
//         )
//     }
// }
