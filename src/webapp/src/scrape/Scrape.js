import React, {Component} from "react";
import "./Scrape.css"
import {Container, Row, Col, Button, ModalFooter} from 'reactstrap';
import axios from "axios";

class Scrape extends Component {

    constructor(props) {
        super(props);
        this.state = {
        };
        this.scrapeCoaches = this.scrapeCoaches.bind(this);
        this.loadResults = this.loadResults.bind(this);
    }

    scrapeCoaches() {
        axios.get('http://localhost:8080/api/scrape/coach/')
            .then(() => {
                this.loadResults();
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    loadResults() {
    }

    render() {
        return (
            <div className="Scrape">
                <Container fluid>
                    <Row>
                        <Button className="Scrape-button" classActiveName="Scrape-button-active" onClick={this.scrapeCoaches}>Get Coaches</Button>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default Scrape;
