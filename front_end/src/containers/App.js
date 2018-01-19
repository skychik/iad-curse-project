import React from 'react';
//import rest from './rest';
import Pile from './Pile';
import Loops from "./loops";
import Mentors from "./mentors";
import Events from "./events";
import Profile from "./profile";
import { Switch, Route } from 'react-router';
import { Link, Redirect } from "react-router-dom";
import LogoImg from '../images/logo.png'
// import client from 'rest'
// import * as actionCreators from "../actions";
// import {bindActionCreators} from "redux";
// import {connect} from "react-redux";
// import reducers from "../reducers"; // TODO: deal with 'connect' decorator


class App extends React.Component {
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
            <div className="App">
                <div className="App-header">
                    <div className="App-header_img-frame">
                        <img className="App-logo" src={LogoImg} />
                        <div className="App-header_fix" />
                    </div>
                    <Link className="App-header_buttons" to='/pile'>
                        <div>Pile</div>
                        <div className="App-header_fix" />
                    </Link>
                    <Link className="App-header_buttons" to='/events'>
                        <div>Events</div>
                        <div className="App-header_fix" />
                    </Link>
                    <Link className="App-header_buttons" to='/mentors'>
                        <div>Mentors</div>
                        <div className="App-header_fix" />
                    </Link>
                    <Link className="App-header_buttons" to='/loops'>
                        <div>Loops</div>
                        <div className="App-header_fix" />
                    </Link>
                    <Link className="App-header_buttons" to='/profile'>
                        <div>Profile</div>
                        <div className="App-header_fix" />
                    </Link>
                    <div className="App-header_fix" />
                </div>
                <Switch>
                    <Route path='/pile' component={Pile} />
                    <Route path='/events' component={Events} />
                    <Route path='/mentors' component={Mentors} />
                    <Route path='/loops' component={Loops} />
                    <Route path='/profile' component={Profile} />
                    <Route render={() => <Redirect to='page_not_found' /> } />
                </Switch>
                <div className="App-footer">

                </div>
            </div>
        );
    }
}

export default App;


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
