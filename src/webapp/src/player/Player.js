import React, {Component} from "react";
import "./Player.css"
import axios from 'axios';
import {Table, Dropdown, DropdownMenu, DropdownItem, DropdownToggle, Container, Row, Badge} from 'reactstrap';

class Player extends Component {
    render() {
        return (
            <div className="Player-div">
                <Container fluid>
                    <Row>
                        <h1><Badge className="Player-table">
                            FSU Player Page
                        </Badge></h1>
                    </Row>
                    <Row>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default Player;
