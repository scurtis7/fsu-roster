import React, {Component} from "react";
import "./Player.css"
import axios from 'axios';
import {Form, FormGroup, Col, Badge, Button, InputGroup, InputGroupAddon, InputGroupText, Input, Row} from 'reactstrap';

class Player extends Component {

    constructor(props) {
        super(props);
        this.state = {
            playerId: '',
            playerName: '',
            position: '',
            year: '',
            redshirt: '',
            classStanding: '',
            jersey: '',
            sport: '',
            active: '',
            status: '',
            height: '',
            weight: '',
            homeTown: '',
            highSchool: '',
            otherCollege: '',
            draftPick: '',
            nflTeam: '',
            notes: '',
            rivalsStars: '',
            rivalsRating: '',
            rivalsRankNational: '',
            rivalsRankPosition: '',
            rivalsRankState: '',
            rivalsLink: '',
            two47Stars: '',
            two47Rating: '',
            two47RankNational: '',
            two47RankPosition: '',
            two47RankState: '',
            two47Link: '',
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handlePlayerNameChange = this.handlePlayerNameChange.bind(this);
    }

    handleSubmit(event) {
        console.log("playerName: " + this.state.playerName);
    }

    handlePlayerNameChange(event) {
        this.setState({playerName: event.currentTarget.value});
    }

    render() {
        return (
            <div className="Player-div">
                <Form onSubmit={this.handleSubmit}>
                    <Col sm={10}>
                        <h1>
                            <Badge className="About-badge">Player</Badge>
                        </h1>
                    </Col>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText value={this.state.playerName} onChange={this.handlePlayerNameChange} className="Player-input-group-text">Full Name</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Name"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Position</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Position"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Year</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Year"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Redshirt</InputGroupText>
                                </InputGroupAddon>
                                <Input type="checkbox" name="redshirt" id="redshirt" className="Player-checkbox"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Jersey</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Jersey"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Sport</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Sport"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Active</InputGroupText>
                                </InputGroupAddon>
                                <Input type="checkbox" name="active" id="active" className="Player-checkbox"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Status</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Status"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Height</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Height"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Weight</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Weight"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Hometown</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Hometown"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">High School</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="High School"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Other College</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Other College"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Draft Pick</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Draft Pick"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">NFL Team</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="NFL Team"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Notes</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Notes"/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <Col sm={10}>
                        <h1>
                            <Badge className="About-badge">Rivals</Badge>
                        </h1>
                    </Col>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Stars</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Stars" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Rating</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Rating" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">National Rank</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="National Rank" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Position Rank</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Position Rank" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">State Rank</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="State Rank" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Rivals Link</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Rivals Link" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <Col sm={10}>
                        <h1>
                            <Badge className="About-badge">247</Badge>
                        </h1>
                    </Col>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Stars</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Stars" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Rating</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Rating" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">National Rank</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="National Rank" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Position Rank</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Position Rank" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">State Rank</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="State Rank" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Rivals Link</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Rivals Link" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 2}}>
                            <Button className="Player-button" classActiveName="Player-button-active">Save</Button>
                        </Col>
                    </FormGroup>
                </Form>
            </div>
        );
    }
}

export default Player;
