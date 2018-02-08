import React from 'react';
//import rest from './rest';
import NewsContainer from './NewsContainer';
import LoopsContainer from "./LoopsContainer";
import MentorsContainer from "./MentorsContainer";
import EventsContainer from "./EventsContainer";
import ProfileContainer from "./ProfileContainer";
import { Switch, Route } from 'react-router';
import { Link, Redirect } from "react-router-dom";
import LogoImg from '../images/logo.png'
// import client from 'rest'
// import * as actionCreators from "../actions";
// import {bindActionCreators} from "redux";
// import {connect} from "react-redux";
// import reducers from "../reducers"; // TODO: deal with 'connect' decorator


class AppContainer extends React.Component {
    // constructor(props) {
    //     super(props);
    //
    //     this.changePage = this.changePage.bind(this);
    // }
//
    // changePage(menuName) {
    //     return this.props.changePage(menuName)
    // }

    render() {
        return (
            <div className="AppContainer">
                <div className="AppContainer-header">
                    {/*<div className="AppContainer-header_img-frame">*/}
                        {/*<img className="AppContainer-logo" src={LogoImg} />*/}
                        {/*/!*<div className="AppContainer-header_fix" />*!/*/}
                    {/*</div>*/}
                    <Link className="AppContainer-header_buttons" to='/feed'>
                        <div>Feed</div>
                        {/*<div className="AppContainer-header_fix" />*/}
                    </Link>
                    <Link className="AppContainer-header_buttons" to='/events'>
                        <div>Events</div>
                        {/*<div className="AppContainer-header_fix" />*/}
                    </Link>
                    <Link className="AppContainer-header_buttons" to='/mentors'>
                        <div>Mentors</div>
                        {/*<div className="AppContainer-header_fix" />*/}
                    </Link>
                    <Link className="AppContainer-header_buttons" to='/loops'>
                        <div>Loops</div>
                        {/*<div className="AppContainer-header_fix" />*/}
                    </Link>
                    <div className="AppContainer-header_spring" />
                    <Link className="AppContainer-header_buttons right-align-block" to='/profile'>
                        <div>Profile</div>
                        {/*<div className="AppContainer-header_fix" />*/}
                    </Link>
                    {/*<div className="AppContainer-header_fix" />*/}
                </div>
                <Switch>
                    <Route path='/feed' component={NewsContainer} />
                    <Route path='/events' component={EventsContainer} />
                    <Route path='/mentors' component={MentorsContainer} />
                    <Route path='/loops' component={LoopsContainer} />
                    <Route path='/profile' component={ProfileContainer} />
                    <Route render={() => <Redirect to='page_not_found' /> } />
                </Switch>
                <div className="AppContainer-footer">

                </div>
            </div>
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
//                     <th>Content</th>
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
