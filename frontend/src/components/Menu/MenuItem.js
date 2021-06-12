import React from "react";
import PropTypes from "prop-types";
import {Nav, NavDropdown } from 'react-bootstrap';

const MenuItem = ({ id, gender, categories }) => {

    return (
        <Nav variant="pills" activeKey="1" onSelect={handleSelect}>
            <Nav.Item>
                <Nav.Link eventKey="1" href="#/home">
                    NavLink 1 content
                </Nav.Link>
            </Nav.Item>
            <Nav.Item>
                <Nav.Link eventKey="2" title="Item">
                    NavLink 2 content
                </Nav.Link>
            </Nav.Item>
            <Nav.Item>
                <Nav.Link eventKey="3" disabled>
                    NavLink 3 content
                </Nav.Link>
            </Nav.Item>
            <NavDropdown title={gender} id="nav-dropdown">
                {
                    categories && categories.map(items => (
                        <NavDropdown.Item key={items.cid} eventKey="4.1">{items.categoryName}</NavDropdown.Item>
                    ))
                }

            </NavDropdown>
        </Nav>

    );
};
MenuItem.propTypes = {
    title: PropTypes.string,
};
export default MenuItem;