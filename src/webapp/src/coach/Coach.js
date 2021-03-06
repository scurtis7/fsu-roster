import React, {Component} from "react";
import "./Coach.css"
import axios from 'axios';
import {Form, FormGroup, Col, Badge, Button, InputGroup, InputGroupAddon, InputGroupText, Input} from 'reactstrap';
import {withRouter} from 'react-router-dom'

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
        this.onChange = this.onChange.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        let coach = {coachId: 0, name: this.state.coachName, position: this.state.coachPosition, sport: this.state.sport};
        axios.post('http://localhost:8080/api/coach', coach)
            .then(this.props.history.push('/coaches'))
            .then(() => {
                this.props.history.push('/coaches');
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

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
                                    <InputGroupText className="Coach-input-group-text">Full Name</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Name" value={this.state.coachName} name="coachName" onChange={this.onChange}/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Coach-input-group-text">Position</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Position" value={this.state.coachPosition} name="coachPosition" onChange={this.onChange}/>
                            </InputGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Coach-input-group-text">Sport</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Sport" value={this.state.sport} name="sport" onChange={this.onChange}/>
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

export default withRouter(Coach);
