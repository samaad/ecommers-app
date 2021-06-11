import React from "react";
import PropTypes from "prop-types";
import Card from "./Card";

const SubProducts = (props) => {

    console.log(props);
    const {
        product = {}
    } = props
    const showSubProduct = () => {

    }

    const getContent = () => {
        return product && product.map(prod => (
            <Card {...prod} />
        ))
    }


    return (
        <>
            {
                product ? getContent() : <div>No sub prodcts</div>
            }
        </>

    );
};
SubProducts.propTypes = {
    title: PropTypes.string,
};
export default SubProducts;