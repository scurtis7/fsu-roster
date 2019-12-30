import React, {Component} from "react";
import "./Roster.css"
import axios from 'axios';
import {Table, Dropdown, DropdownMenu, DropdownItem, DropdownToggle} from 'reactstrap';

class Coach extends Component {

    constructor(props) {
        super(props);
        this.state = {
            players: [],
            positions: [],
            dropdownOpen: false,
        };
        this.setPositions = this.setPositions.bind(this);
        this.setPlayers = this.setPlayers.bind(this);
        this.loadPlayerTable = this.loadPlayerTable.bind(this);
        this.getPositions = this.getPositions.bind(this);
        this.getRecruits = this.getRecruits.bind(this);
        this.toggle = this.toggle.bind(this);
        this.positionSelected = this.positionSelected.bind(this);
    }

    setPositions(positions) {
        this.setState({positions});
    }

    setPlayers(players) {
        this.setState({players});
    }

    toggle() {
        this.setState({
            dropdownOpen: !this.state.dropdownOpen
        });
    }

    positionSelected(e) {
        this.getRecruits(e.currentTarget.getAttribute("id"));
    }

    getPositions() {
        axios(`http://localhost:8080/api/positions`)
            .then(positions => this.setPositions(positions.data))
            .catch(error => error);
    }

    getRecruits(position) {
        axios(`http://localhost:8080/api/recruits/` + position)
            .then(players => this.setPlayers(players.data))
            .catch(error => error);
    }

    loadPositionDropdown() {
        if (this.state.positions === null) {
            // do nothing
        } else {
            return this.state.positions.map((position, index) => {
                return (
                    <DropdownItem id={position} onClick={this.positionSelected}>{position}</DropdownItem>
                );
            });
        }
    }

    loadPlayerTable() {
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
        this.getPositions();
        this.getRecruits('ALL');
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
                                    {this.loadPositionDropdown()}
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
                    {this.loadPlayerTable()}
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default Coach;
