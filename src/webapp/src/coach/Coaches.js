import React, {Component} from "react";
import "./Coaches.css"
import axios from 'axios';
import {Table, Modal, ModalHeader, ModalBody, ModalFooter} from 'reactstrap';
import Button from "reactstrap/es/Button";

class Coaches extends Component {

    constructor(props) {
        super(props);
        this.state = {
            coaches: [],
            modal: false,
        };
        this.setCoaches = this.setCoaches.bind(this);
        this.loadTableData = this.loadTableData.bind(this);
        this.deleteCoach = this.deleteCoach.bind(this);
        this.toggle = this.toggle.bind(this);
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
                            <Button className="Player-button" classActiveName="Player-button-active"onClick={() => this.toggle()}>Edit</Button>
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
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.toggle}>Do Something</Button>{' '}
                        <Button color="secondary" onClick={this.toggle}>Cancel</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

export default Coaches;
