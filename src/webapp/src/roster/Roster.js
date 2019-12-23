import React, {Component} from "react";
import "./Roster.css"
import axios from 'axios';
import {Table} from 'reactstrap';

class Coach extends Component {

    constructor(props) {
        super(props);
        this.state = {
            players: [],
        };
        this.setPlayers = this.setPlayers.bind(this);
        this.loadTableData = this.loadTableData.bind(this);
    }

    setPlayers(players) {
        this.setState({players});
    }

    loadTableData() {
        if (this.state.players === null) {
            return (
                <tr><td colSpan="6">Loading...</td></tr>
            );
        } else {
            return this.state.players.map((player, index) => {
                const {id, name, position, year, redshirt, jersey, sport, status, height, weight, homeTown, highSchool, otherCollege} = player; //destructuring
                return (
                    <tr key={index}>
                        <td>{id}</td>
                        <td>{name}</td>
                        <td>{position}</td>
                        <td>{year}</td>
                        <td>{redshirt}</td>
                        <td>{jersey}</td>
                        <td>{sport}</td>
                        <td>{status}</td>
                        <td>{height}</td>
                        <td>{weight}</td>
                        <td>{homeTown}</td>
                        <td>{highSchool}</td>
                        <td>{otherCollege}</td>
                    </tr>
                );
            });
        }
    }

    componentDidMount() {
        axios(`http://localhost:8080/api/players`)
            .then(players => this.setPlayers(players.data))
            .catch(error => error);
    }

    render() {
        return (
            <div className="Roster-div">
                <Table className="Roster-table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Year</th>
                        <th>Redshirt</th>
                        <th>Jersey</th>
                        <th>Sport</th>
                        <th>Status</th>
                        <th>Height</th>
                        <th>Weight</th>
                        <th>Home Town</th>
                        <th>High School</th>
                        <th>Other College</th>
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
