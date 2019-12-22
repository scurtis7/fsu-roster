import React, {Component} from "react";
import logo from './FSU_Logo.png';
import './Home.css';
import {Container, Row, Badge} from 'reactstrap';

class Home extends Component {
    render() {
        return (
            <Container fluid>
                <div className="Home">
                    <header className="Home-header">
                        <img src={logo} className="Home-logo" alt="logo" />
                        <a
                            className="Home-link"
                            href="https://seminoles.com/sports/football/roster/"
                            target="_blank"
                            rel="noopener noreferrer"
                        >
                            FSU Seminoles Roster
                        </a>
                    </header>
                </div>
            </Container>
        );
    }
}

export default Home;
