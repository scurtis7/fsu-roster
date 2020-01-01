import React, {Component} from "react";
import "./Roster.css"
import axios from 'axios';
import {Table, Dropdown, DropdownMenu, DropdownItem, DropdownToggle} from 'reactstrap';

class Roster extends Component {

    constructor(props) {
        super(props);
        this.state = {
            players: [],
            positions: [],
            jerseys: [],
            positionDropdownOpen: false,
            jerseyDropdownOpen: false,
        };
        this.setPositions = this.setPositions.bind(this);
        this.setJerseys = this.setJerseys.bind(this);
        this.setPlayers = this.setPlayers.bind(this);
        this.togglePositionDropdown = this.togglePositionDropdown.bind(this);
        this.toggleJerseyDropdown = this.toggleJerseyDropdown.bind(this);
        this.positionSelected = this.positionSelected.bind(this);
        this.jerseySelected = this.jerseySelected.bind(this);
        this.getPositions = this.getPositions.bind(this);
        this.getJerseys = this.getJerseys.bind(this);
        this.getRecruits = this.getRecruits.bind(this);
        this.getRecruitsByJersey = this.getRecruitsByJersey.bind(this);
        this.loadPositionDropdown = this.loadPositionDropdown.bind(this);
        this.loadJerseyDropdown = this.loadJerseyDropdown.bind(this);
        this.loadPlayerTable = this.loadPlayerTable.bind(this);
    }

    setPositions(positions) {
        this.setState({positions});
    }

    setJerseys(jerseys) {
        this.setState({jerseys});
    }

    setPlayers(players) {
        this.setState({players});
    }

    togglePositionDropdown() {
        this.setState({
            positionDropdownOpen: !this.state.positionDropdownOpen
        });
    }

    toggleJerseyDropdown() {
        this.setState({
            jerseyDropdownOpen: !this.state.jerseyDropdownOpen
        });
    }

    positionSelected(e) {
        this.getRecruits(e.currentTarget.getAttribute("id"));
    }

    jerseySelected(e) {
        this.getRecruitsByJersey(e.currentTarget.getAttribute("id"));
    }

    getPositions() {
        axios(`http://localhost:8080/api/positions`)
            .then(positions => this.setPositions(positions.data))
            .catch(error => error);
    }

    getJerseys() {
        axios(`http://localhost:8080/api/jerseys`)
            .then(jerseys => this.setJerseys(jerseys.data))
            .catch(error => error);
    }

    getRecruits(position) {
        axios(`http://localhost:8080/api/recruits/` + position)
            .then(players => this.setPlayers(players.data))
            .catch(error => error);
    }

    getRecruitsByJersey(jersey) {
        axios(`http://localhost:8080/api/recruits/jersey/` + jersey)
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

    loadJerseyDropdown() {
        if (this.state.jerseys === null) {
            // do nothing
        } else {
            return this.state.jerseys.map((jersey, index) => {
                return (
                    <DropdownItem id={jersey} onClick={this.jerseySelected}>{jersey}</DropdownItem>
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
        this.getJerseys();
        this.getRecruits('ALL');
    }

    render() {
        return (
            <div className="Roster-div">
                <Table className="Roster-table">
                    <tr>
                        <td>
                            <Dropdown isOpen={this.state.positionDropdownOpen} toggle={this.togglePositionDropdown}>
                                <DropdownToggle className="dropdown-toggle" activeClassName="dropdown-toggle-active" caret>Select Position</DropdownToggle>
                                <DropdownMenu right>
                                    {this.loadPositionDropdown()}
                                </DropdownMenu>
                            </Dropdown>
                        </td>
                        <td>
                            <Dropdown isOpen={this.state.jerseyDropdownOpen} toggle={this.toggleJerseyDropdown}>
                                <DropdownToggle className="dropdown-toggle" activeClassName="dropdown-toggle-active" caret>Select Jersey</DropdownToggle>
                                <DropdownMenu right>
                                    {this.loadJerseyDropdown()}
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

export default Roster;
