import React, {Component} from "react";
import "./Roster.css"
import axios from 'axios';
import {Table, Dropdown, DropdownMenu, DropdownItem, DropdownToggle} from 'reactstrap';

class Coach extends Component {

    constructor(props) {
        super(props);
        this.state = {
            players: [],
            dropdownOpen: false,
        };
        this.setPlayers = this.setPlayers.bind(this);
        this.loadTableData = this.loadTableData.bind(this);
        this.getRecruits = this.getRecruits.bind(this);
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            dropdownOpen: !this.state.dropdownOpen
        });
    }

    setPlayers(players) {
        this.setState({players});
    }

    getRecruits() {
        axios(`http://localhost:8080/api/recruits`)
            .then(players => this.setPlayers(players.data))
            .catch(error => error);
    }

    loadTableData() {
        if (this.state.players === null) {
            return (
                <tr><td colSpan="6">Loading...</td></tr>
            );
        } else {
            return this.state.players.map((player, index) => {
                const {jersey, name, position, classStanding, height, weight, rivalsStars, rivalsRating, rivalsRankPosition, rivalsRankState, two47Stars, two47Rating, two47RankNational, two47RankPosition, two47RankState} = player; //destructuring
                return (
                    <tr key={index}>
                        <td>{jersey}</td>
                        <td>{name}</td>
                        <td>{position}</td>
                        <td>{classStanding}</td>
                        <td>{height}</td>
                        <td>{weight}</td>
                        <td>{rivalsStars}</td>
                        <td>{rivalsRating}</td>
                        <td>{rivalsRankPosition}</td>
                        <td>{rivalsRankState}</td>
                        <td>{two47Stars}</td>
                        <td>{two47Rating}</td>
                        <td>{two47RankNational}</td>
                        <td>{two47RankPosition}</td>
                        <td>{two47RankState}</td>
                    </tr>
                );
            });
        }
    }

    componentDidMount() {
        this.getRecruits();
    }

    render() {
        return (
            <div className="Roster-div">
                <Table className="Roster-table">
                    <tr>
                        <td>
                            <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle}>
                                <DropdownToggle className="dropdown-toggle" activeClassName="dropdown-toggle-active" caret>Select Position</DropdownToggle>
                                <DropdownMenu right>
                                    <DropdownItem onClick={this.getRecruits}>Quarterback</DropdownItem>
                                    <DropdownItem>Running Back</DropdownItem>
                                    <DropdownItem>Wide Receiver</DropdownItem>
                                    <DropdownItem>Tight End</DropdownItem>
                                    <DropdownItem>Offensive Line</DropdownItem>
                                    <DropdownItem>Defensive Line</DropdownItem>
                                    <DropdownItem>Linebacker</DropdownItem>
                                    <DropdownItem>Cornerback</DropdownItem>
                                </DropdownMenu>
                            </Dropdown>
                        </td>
                    </tr>
                </Table>
                <Table className="Roster-table">
                    <thead>
                    <tr>
                        <th>Jersey</th>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Class</th>
                        <th>Height</th>
                        <th>Weight</th>
                        <th>Rivals Stars</th>
                        <th>Rivals Rating</th>
                        <th>Rivals Position Rank</th>
                        <th>Rivals State Rank</th>
                        <th>247 Stars</th>
                        <th>247 Rating</th>
                        <th>247 National Rank</th>
                        <th>247 Position Rank</th>
                        <th>247 State Rank</th>
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
