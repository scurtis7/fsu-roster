import React, {Component} from "react";
import "./Coaches.css"
import axios from 'axios';
import {
    Table,
    Modal,
    ModalHeader,
    ModalBody,
    ModalFooter,
    FormGroup,
    Col,
    InputGroup,
    InputGroupAddon, InputGroupText, Input, Form
} from 'reactstrap';
import Button from "reactstrap/es/Button";

class Coaches extends Component {

    constructor(props) {
        super(props);
        this.state = {
            coaches: [],
            coachId: '',
            coachName: '',
            coachPosition: '',
            sport: '',
            modal: false,
        };
        this.setCoaches = this.setCoaches.bind(this);
        this.loadTableData = this.loadTableData.bind(this);
        this.deleteCoach = this.deleteCoach.bind(this);
        this.toggle = this.toggle.bind(this);
        this.editCoach = this.editCoach.bind(this);
        this.onChange = this.onChange.bind(this);
        this.saveCoach = this.saveCoach.bind(this);
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    editCoach(coach) {
        this.setState({coachId: coach.coachId});
        this.setState({coachName: coach.name});
        this.setState({coachPosition: coach.position});
        this.setState({sport: coach.sport});
        this.toggle();
    }

    saveCoach() {
        let coach = {coachId: this.state.coachId, name: this.state.coachName, position: this.state.coachPosition, sport: this.state.sport};
        axios.post('http://localhost:8080/api/coach/' + coach.coachId, coach)
            .then(this.props.history.push('/coaches'))
            .catch(function (error) {
                console.log(error);
            });
    }

    toggle() {
        this.setState({modal: !this.state.modal});
    }

    setCoaches(coaches) {
        this.setState({coaches});
    }

    deleteCoach(id) {
        axios.delete('http://localhost:8080/api/coach/' + id)
            .then(response => this.setCoaches(response.data))
            .catch(error => error);
    }

    loadTableData() {
        if (this.state.coaches === null) {
            return (
                <tr><td colSpan="6">Loading...</td></tr>
            );
        } else {
            return this.state.coaches.map((coach, index) => {
                const {coachId, name, position, sport} = coach; //destructuring
                return (
                    <tr key={index}>
                        <td>{coachId}</td>
                        <td>{name}</td>
                        <td>{position}</td>
                        <td>{sport}</td>
                        <td>
                            <Button className="Player-button" classActiveName="Player-button-active" onClick={() => this.deleteCoach(coachId)}>Delete</Button>
                            <Button className="Player-button" classActiveName="Player-button-active" onClick={() => this.editCoach(coach)}>Edit</Button>
                        </td>
                    </tr>
                );
            });
        }
    }

    componentDidMount() {
        axios('http://localhost:8080/api/coaches')
            .then(coaches => this.setCoaches(coaches.data))
            .catch(error => error);
    }

    render() {
        return (
            <div className="Coach-div">
                <Table className="Coach-table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Sport</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.loadTableData()}
                    </tbody>
                </Table>
                <Modal isOpen={this.state.modal} toggle={this.toggle}>
                    <ModalHeader toggle={this.toggle}>Edit Coach</ModalHeader>
                    <ModalBody>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Coach-input-group-text">Full Name</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Name" value={this.state.coachName} name="coachName" onChange={this.onChange}/>
                            </InputGroup>
                        </Col>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Coach-input-group-text">Position</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Position" value={this.state.coachPosition} name="coachPosition" onChange={this.onChange}/>
                            </InputGroup>
                        </Col>
                        <Col sm={{size: 10, offset: 1}}>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText className="Coach-input-group-text">Sport</InputGroupText>
                                </InputGroupAddon>
                                <Input placeholder="Sport" value={this.state.sport} name="sport" onChange={this.onChange}/>
                            </InputGroup>
                        </Col>
                    </ModalBody>
                    <ModalFooter>
                        <Button className="Player-button" classActiveName="Player-button-active" onClick={this.saveCoach}>Save</Button>{' '}
                        <Button color="secondary" onClick={this.toggle}>Cancel</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

export default Coaches;
