import React, {Component} from "react";
import "./Coaches.css"
import axios from 'axios';
import {Table} from 'reactstrap';
import Button from "reactstrap/es/Button";

class Coaches extends Component {

    constructor(props) {
        super(props);
        this.state = {
            coaches: [],
        };
        this.setCoaches = this.setCoaches.bind(this);
        this.loadTableData = this.loadTableData.bind(this);
        this.deleteCoach = this.deleteCoach.bind(this);
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
                            <Button className="Player-button" classActiveName="Player-button-active">Edit</Button>
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
            </div>
        );
    }
}

export default Coaches;
