import React, {Component} from 'react';
import "./App.css";
import Home from "./home/Home";
import Roster from "./roster/Roster";
import Coach from "./coach/Coach"
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
                                <NavItem><NavLink className="navlink" activeClassName="navlinkactive" to="/coach">Coaches</NavLink></NavItem>
                            </Nav>
                            <Nav className="ml-auto" navbar>
                                <NavItem><NavLink className="navlink" activeClassName="navlinkactive" to="/about">About</NavLink></NavItem>
                            </Nav>
                        </Collapse>
                    </Navbar>
                </div>
                <div className="content">
                    <Route exact path="/" component={Home}/>
                    <Route path="/roster" component={Roster}/>
                    <Route path="/coach" component={Coach}/>
                    <Route path="/about" component={About}/>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
