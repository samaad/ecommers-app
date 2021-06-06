import React from "react";
import PropTypes from "prop-types";
import {Nav, NavDropdown} from "react-bootstrap";


const MenuBar = ({ menuOptions, setSelectCategory }) => {
    const handleSelect = data => {
       const selectCategory =JSON.parse(data)
        console.log(selectCategory);
        setSelectCategory(selectCategory)
    }
    return (
        <div style={menubarStyle}>
            {
                menuOptions && menuOptions.map(({id, gender, categories}) => (
                    <Nav key={id} variant="pills" activeKey="1" onSelect={handleSelect}>
                        <NavDropdown key={id} title={gender} id="nav-dropdown">
                            {
                                categories && categories.map(items => (
                                    <NavDropdown.Item key={items.id} eventKey={JSON.stringify(items)}>{items.categoryName}</NavDropdown.Item>
                                ))
                            }
                        </NavDropdown>
                    </Nav>
            ))}
        </div>
    );
};

const menubarStyle = {
    display: "flex",
    justifyContent: "center",
    padding: "10px",
    color: "fff",
};


MenuBar.defaultProps = {
    title: "User Registration",
};

MenuBar.propTypes = {
    title: PropTypes.string,
};

export default MenuBar;