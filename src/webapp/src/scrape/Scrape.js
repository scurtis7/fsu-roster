import React, {Component} from "react";
import "./Scrape.css"
import {Container, Row, Col, Button, ModalFooter} from 'reactstrap';
import axios from "axios";

class Scrape extends Component {

    constructor(props) {
        super(props);
        this.state = {
            results: [],
        };
        this.scrapeCoaches = this.scrapeCoaches.bind(this);
        this.scrapePlayers = this.scrapePlayers.bind(this);
        this.setResults = this.setResults.bind(this);
        this.loadTableData = this.loadTableData.bind(this);
    }

    scrapeCoaches() {
        this.setResults([]);
        axios.get('http://localhost:8080/api/scrape/coach/')
            .then(results => this.setResults(results.data))
            .catch(function (error) {
                console.log(error);
            });
    }

    scrapePlayers() {
        this.setResults([]);
        axios.get('http://localhost:8080/api/scrape/player/')
            .then(results => this.setResults(results.data))
            .catch(function (error) {
                console.log(error);
            });
    }

    setResults(results) {
        this.setState({results});
    }

    loadTableData() {
        if (this.state.results === null) {
            return (
                <Row className="Scrape-row">
                    Results
                </Row>
            );
        } else {
            return this.state.results.map((result, index) => {
                return (
                    <Row className="Scrape-row">
                        {result}
                    </Row>
                );
            });
        }
    }

    render() {
        return (
            <div className="Scrape">
                <Container fluid>
                    <Row>
                        <Col>
                            <Button className="Scrape-button" classActiveName="Scrape-button-active" onClick={this.scrapeCoaches}>Get Coaches</Button>
                        </Col>
                        <Col>
                            <Button className="Scrape-button" classActiveName="Scrape-button-active" onClick={this.scrapePlayers}>Get Players</Button>
                        </Col>
                    </Row>
                    <Row><br/></Row>
                    {this.loadTableData()}
                </Container>
            </div>
        );
    }
}

export default Scrape;
