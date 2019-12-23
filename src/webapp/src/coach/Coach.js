import React, {Component} from "react";
import "./Coach.css"
import axios from 'axios';
import {Table} from 'reactstrap';

class Coach extends Component {

    constructor(props) {
        super(props);
        this.state = {
            coaches: [],
        };
        this.setCoaches = this.setCoaches.bind(this);
        this.loadTableData = this.loadTableData.bind(this);
    }

    setCoaches(coaches) {
        this.setState({coaches});
    }

    loadTableData() {
        if (this.state.coaches === null) {
            return (
                <tr><td colSpan="6">Loading...</td></tr>
            );
        } else {
            return this.state.coaches.map((coach, index) => {
                const {id, name, position, sport} = coach; //destructuring
                return (
                    <tr key={index}>
                        <td>{id}</td>
                        <td>{name}</td>
                        <td>{position}</td>
                        <td>{sport}</td>
                    </tr>
                );
            });
        }
    }

    componentDidMount() {
        axios(`http://localhost:8080/api/coaches`)
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

export default Coach;
