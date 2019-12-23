import React, {Component} from "react";
import "./About.css"
import {Container, Row, Badge} from 'reactstrap';

class About extends Component {
    render() {
        return (
            <div className="About">
                <Container fluid>
                    <Row>
                        <h1><Badge className="About-badge">
                            FSU Roster Application
                        </Badge></h1>
                    </Row>
                    <Row>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default About;
