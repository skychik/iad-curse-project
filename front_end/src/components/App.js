import React from 'react';
import '../styles/App.css';
//import client from './client';
import Pile from './pile';
import Loops from "./loops";
import Mentors from "./mentors";
import Events from "./events";
import Profile from "./profile";
import * as actionCreators from "../actions";
import {bindActionCreators} from 'redux';
import { connect } from 'react-redux';
import { Switch, Route } from 'react-router';
import {Link, Redirect} from "react-router-dom";
import Img from 'react-image'
// import reducers from "../reducers"; // TODO: deal with 'connect' decorator


class App extends React.Component {
    constructor(props) {
        super(props);

        this.changePage = this.changePage.bind(this);

        this.state = {
            menus : [
                'Pile',
                'Events',
                'Mentors',
                'Loops',
                'Profile'
            ]
        }
    }

    // componentDidMount() {
    //     client({method: 'GET', path: '/api/news'}).done(response => {
    //         this.setState({news: response.entity._embedded.news});
    //     });
    // }

    changePage(menuName) {
        return this.props.changePage(menuName)
    }

    render() {
        return (
            <div className="App">
                <div className="App-header">
                    <Img className="App-logo" src="../images/logo.png" />
                    <Link to='/pile'><button>Pile</button></Link>
                    <Link to='/events'><button>Events</button></Link>
                    <Link to='/mentors'><button>Mentors</button></Link>
                    <Link to='/loops'><button>Loops</button></Link>
                    <Link to='/profile'><button>Profile</button></Link>
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

const mapStateToProps = (state) => {
    return state;
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
