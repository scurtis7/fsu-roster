import React, {Component} from "react";
import "./Scrape.css"
import {Container, Row, Col, Button, ModalFooter} from 'reactstrap';
import axios from "axios";

class Scrape extends Component {

    constructor(props) {
        super(props);
        this.state = {
            coaches: [],
        };
        this.scrapeCoaches = this.scrapeCoaches.bind(this);
        this.setCoaches = this.setCoaches.bind(this);
        this.loadTableData = this.loadTableData.bind(this);
    }

    scrapeCoaches() {
        axios.get('http://localhost:8080/api/scrape/coach/')
            .then(coaches => this.setCoaches(coaches.data))
            .catch(function (error) {
                console.log(error);
            });
    }

    setCoaches(coaches) {
        this.setState({coaches});
    }

    loadTableData() {
        if (this.state.coaches === null) {
            return (
                <Row>
                    Results
                </Row>
            );
        } else {
            return this.state.coaches.map((coach, index) => {
                return (
                    <Row className="Coach-row">
                        {coach}
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
                        <Button className="Scrape-button" classActiveName="Scrape-button-active" onClick={this.scrapeCoaches}>Get Coaches</Button>
                    </Row>
                    <Row><br/></Row>
                    {this.loadTableData()}
                </Container>
            </div>
        );
    }
}

export default Scrape;
