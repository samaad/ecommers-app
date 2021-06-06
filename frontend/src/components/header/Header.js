import React from "react";
import PropTypes from "prop-types";

const Header = ({ title }) => {
    return (
        <header style={headerStyle} className="header">
            <h2>{title}</h2>
        </header>
    );
};


const headerStyle = {
    display: "flex",
    justifyContent: "center",
    padding: "10px",
    color: "fff",
};

Header.defaultProps = {
    title: "User Registration",
};

Header.propTypes = {
    title: PropTypes.string,
};

export default Header;