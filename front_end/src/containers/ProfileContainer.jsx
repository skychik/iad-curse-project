import React  from 'react';
import * as actionCreators from "../actions";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import RestClient from "another-rest-client";
import {Button, Col, Glyphicon, Image, Row} from "react-bootstrap";
import NewsPreview from "../components/NewsPreview";
import {Link, Route, Switch} from "react-router-dom";

class ProfileContainer extends React.Component {
    componentDidMount() {
        let api = new RestClient('http://localhost:8080'); // TODO: make static
        api.res("user");
        const promise = api.user(1).get();
        promise.then((response) => {
            const profile = JSON.parse(JSON.stringify(response));
            console.log(profile);
            this.setProfile(profile);
        });
    }

    setProfile(profile) {
        return this.props.setProfile(profile)
    }

    render() {
        if (!this.props.profile) return "Loading...";

        const { id, username, firstName, surname, patronymic, birthDate, sex, photo,
            news, performers } = this.props.profile;
        const feedContainer =  news != null ?
            news.map((newsPreview, idx) => {
                return <NewsPreview className="NewsPreview"
                                    key={idx}
                                    newsId={newsPreview.id}
                                    authorId={newsPreview.author.id}
                                    authorUsername={newsPreview.author.username}
                                    authorAvatar={newsPreview.author.photo}
                                    title={newsPreview.title}
                                    contentPreview={newsPreview.contentPreview}
                                    creationDate={newsPreview.creationDate}
                                    alteringDate={newsPreview.alteringDate}
                                    commentsNumber={newsPreview.commentsNumber}
                                    loopsNumber={newsPreview.loopsNumber}
                                    poopsNumber={newsPreview.poopsNumber}
                                    repostsNumber={newsPreview.repostsNumber}
                />;
            }) : "No news";
        const performersContainer = performers != null ?
            performers.map((performer, idx) => {
                return <Link key={idx}>
                    to={performer}
                </Link>
            }) : "No performers";

        return <Row className="profile">
            <Col md={3}>
                <div className="profile-sidebar">
                    <div className="profile-userpic">
                        <Image className="full_avatar" src={"/photo/usr/" + photo} alt="logo" responsive/>
                    </div>
                    <div className="profile-usertitle">
                        <div className="profile-usertitle-name">
                            <h3>@{username}</h3>
                            <h4>{firstName} {surname} {patronymic}</h4>
                        </div>
                        <div className="profile-usertitle-performer">
                            Performers...
                        </div>
                    </div>
                    <div className="profile-userbuttons">
                        <Button className="btn btn-success btn-sm">Follow</Button>
                    </div>
                    <div className="profile-usermenu">
                        <ul className="nav">
                            <li className="active">
                                <Link href="#">
                                    <Glyphicon glyph="home" /> News
                                </Link>
                            </li>
                            <li className="active">
                                <Link href="#">
                                    <Glyphicon glyph="home" /> Performers
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </Col>
            <Col md={9}>
                <div className="profile-content">
                    <Switch>
                        <Route path='/profile/performers' component={performersContainer} />
                        <Route component={feedContainer} />
                    </Switch>
                </div>
            </Col>
        </Row>
    }
}

const mapStateToProps = (state) => {
    return {profile: state.profile}
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators(actionCreators, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(ProfileContainer);