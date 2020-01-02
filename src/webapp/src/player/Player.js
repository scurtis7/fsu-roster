import React, {Component} from "react";
import "./Player.css"
import axios from 'axios';
import {Form, FormGroup, Col, Label, Button, InputGroup, InputGroupAddon, InputGroupText, Input} from 'reactstrap';

class Player extends Component {
    render() {
        return (
            <div className="Player-div">
                <Form>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Id</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Id" disabled/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Player-input-group-text">Full Name</InputGroupText>
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
