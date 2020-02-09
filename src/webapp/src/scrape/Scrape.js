import React, {Component} from "react";
import "./Scrape.css"
import {Container, Row, Col, Button, Input, InputGroup, InputGroupAddon, InputGroupText} from 'reactstrap';
import axios from "axios";

class Scrape extends Component {

    constructor(props) {
        super(props);
        this.state = {
            results: [],
            rivalsYear: '2020',
            two47Year: '2020',
        };
        this.scrapeCoaches = this.scrapeCoaches.bind(this);
        this.scrapePlayers = this.scrapePlayers.bind(this);
        this.scrapeRivals = this.scrapeRivals.bind(this);
        this.scrape247 = this.scrape247.bind(this);
        this.setResults = this.setResults.bind(this);
        this.loadTableData = this.loadTableData.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

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

    scrapeRivals() {
        this.setResults([]);
        axios.get('http://localhost:8080/api/scrape/rivals/' + this.state.rivalsYear)
            .then(results => this.setResults(results.data))
            .catch(function (error) {
                console.log(error);
            });
    }

    scrape247() {
        this.setResults([]);
        axios.get('http://localhost:8080/api/scrape/247/' + this.state.two47Year)
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
                        <Col/>
                        <Col/>
                        <Col>
                            <InputGroup size="sm">
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Scrape-input-group-text">Year</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Year" value={this.state.rivalsYear} name="rivalsYear" onChange={this.onChange} type="number" min={1990} max={2050} />
                            </InputGroup>
                        </Col>
                        <Col>
                            <InputGroup size="sm">
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Scrape-input-group-text">Year</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Year" value={this.state.two47Year} name="two47Year" onChange={this.onChange} type="number" min={1990} max={2050} />
                            </InputGroup>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Button className="Scrape-button" classActiveName="Scrape-button-active" onClick={this.scrapeCoaches}>Get Coaches</Button>
                        </Col>
                        <Col>
                            <Button className="Scrape-button" classActiveName="Scrape-button-active" onClick={this.scrapePlayers}>Get Players</Button>
                        </Col>
                        <Col>
                            <Button className="Scrape-button" classActiveName="Scrape-button-active" onClick={this.scrapeRivals}>Get Rivals Recruits</Button>
                        </Col>
                        <Col>
                            <Button className="Scrape-button" classActiveName="Scrape-button-active" onClick={this.scrape247}>Get 247 Recruits</Button>
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
