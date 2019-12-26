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
                const {jersey, name, position, classStanding, height, weight, stars, rating, rankPosition, rankState} = player; //destructuring
                return (
                    <tr key={index}>
                        <td>{jersey}</td>
                        <td>{name}</td>
                        <td>{position}</td>
                        <td>{classStanding}</td>
                        <td>{height}</td>
                        <td>{weight}</td>
                        <td>{stars}</td>
                        <td>{rating}</td>
                        <td>{rankPosition}</td>
                        <td>{rankState}</td>
                    </tr>
                );
            });
        }
    }

    componentDidMount() {
        axios(`http://localhost:8080/api/recruits`)
            .then(players => this.setPlayers(players.data))
            .catch(error => error);
    }

    render() {
        return (
            <div className="Roster-div">
                <Table className="Roster-table">
                    <thead>
                    <tr>
                        <th>Jersey</th>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Class</th>
                        <th>Height</th>
                        <th>Weight</th>
                        <th>Stars</th>
                        <th>Rating</th>
                        <th>Position Rank</th>
                        <th>State Rank</th>
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
