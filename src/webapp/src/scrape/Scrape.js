import React, {Component} from "react";
import "./Scrape.css"
import {Container, Row, Badge} from 'reactstrap';

class Scrape extends Component {
    render() {
        return (
            <div className="Scrape">
                <Container fluid>
                    <Row>
                        <h1><Badge className="Scrape-badge">
                            Scrape Page
                        </Badge></h1>
                    </Row>
                    <Row>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default Scrape;
