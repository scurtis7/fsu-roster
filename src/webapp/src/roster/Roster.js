import React, {Component} from "react";
import "./Roster.css"
import {Container, Row, Badge} from 'reactstrap';

class Roster extends Component {
    render() {
        return (
            <div className="Roster-div">
                <Container fluid>
                    <Row>
                        <h1><Badge color="info">Roster</Badge></h1>
                    </Row>
                    <Row>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default Roster;
