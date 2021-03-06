import React, {Component} from 'react';
import "./App.css";
import Home from "./home/Home";
import Roster from "./roster/Roster";
import Player from "./player/Player";
import Coaches from "./coach/Coaches"
import Coach from "./coach/Coach"
import Scrape from "./scrape/Scrape"
import About from "./about/About";
import {BrowserRouter, Route, NavLink, Link} from "react-router-dom";
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem} from "reactstrap";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return (
            <BrowserRouter>
                <div>
                    <Navbar color="dark" dark expand="md">
                        <NavbarBrand tag={Link} exact="true" to="/">Home</NavbarBrand>
                        <NavbarToggler onClick={this.toggle}/>
                        <Collapse isOpen={this.state.isOpen} navbar>
                            <Nav className="mr-auto" navbar>
                                <NavItem><NavLink className="navlink" activeClassName="navlinkactive" to="/roster">Roster</NavLink></NavItem>
                                <NavItem><NavLink className="navlink" activeClassName="navlinkactive" to="/player">Player</NavLink></NavItem>
                                <NavItem><NavLink className="navlink" activeClassName="navlinkactive" to="/coaches">Coaches</NavLink></NavItem>
                                <NavItem><NavLink className="navlink" activeClassName="navlinkactive" to="/coach">Coach</NavLink></NavItem>
                                <NavItem><NavLink className="navlink" activeClassName="navlinkactive" to="/scrape">Scrape</NavLink></NavItem>
                            </Nav>
                            <Nav className="ml-auto" navbar>
                                <NavItem><NavLink className="navlink" activeClassName="navlinkactive" to="/about">About</NavLink></NavItem>
                            </Nav>
                        </Collapse>
                    </Navbar>
                </div>
                <div className="content">
                    <Route exact path="/" component={Home}/>
                    <Route exact path="/roster" component={Roster}/>
                    <Route exact path="/player" component={Player}/>
                    <Route exact path="/coaches" component={Coaches}/>
                    <Route exact path="/coach" component={Coach}/>
                    <Route exact path="/scrape" component={Scrape}/>
                    <Route exact path="/about" component={About}/>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
