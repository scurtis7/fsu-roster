import React, {Component} from "react";
import "./Coach.css"
import axios from 'axios';
import {Form, FormGroup, Col, Badge, Button, InputGroup, InputGroupAddon, InputGroupText, Input, Row} from 'reactstrap';

class Coach extends Component {

    constructor(props) {
        super(props);
        this.state = {
            coachId: '',
            coachName: '',
            coachPosition: '',
            sport: '',
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleCoachNameChange = this.handleCoachNameChange.bind(this);
        this.handlePositionChange = this.handlePositionChange.bind(this);
        this.handleSportChange = this.handleSportChange.bind(this);
    }

    handleSubmit(event) {
        console.log("coachName: " + this.state.playerName);
        event.preventDefault();
        // let coach = {coachId: 0, coachName: this.state.coachName, coachPosition: this.state.coachPosition, sport: 'Football'};
        axios.post('http://localhost:8080/api/coach', {
            coachId: '0',
            name: this.state.coachName,
            position: this.state.coachPosition,
            sport: this.state.sport
        })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    handleCoachNameChange(event) {
        this.setState({coachName: event.currentTarget.value});
    }

    handlePositionChange(event) {
        this.setState({coachPosition: event.currentTarget.value});
    }

    handleSportChange(event) {
        this.setState({sport: event.currentTarget.value});
    }

    render() {
        return (
            <div className="Coach-div">
                <Form onSubmit={this.handleSubmit}>
                    <Col sm={10}>
                        <h1>
                            <Badge className="About-badge">Coach</Badge>
                        </h1>
                    </Col>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText value={this.state.coachName} onChange={this.handleCoachNameChange} className="Coach-input-group-text">Full Name</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Name"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText value={this.state.position} onChange={this.handlePositionChange} className="Coach-input-group-text">Position</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Position"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText value={this.state.sport} onChange={this.handleSportChange} className="Coach-input-group-text">Sport</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Sport"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 2}}>
                            <Button className="Coach-button" classActiveName="Coach-button-active">Save</Button>
                        </Col>
                    </FormGroup>
                </Form>
            </div>
        );
    }
}

export default Coach;
