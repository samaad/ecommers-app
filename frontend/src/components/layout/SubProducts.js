import React from "react";
import PropTypes from "prop-types";
import Card from "./Card";

const SubProducts = (props) => {

    console.log(props);
    const {
        product = {},
        setSubProduct
    } = props
    const showSubProduct = () => {

    }

    const getContent = () => (
        <div className="home">
            <div className="home__row">
                {product && product.map(prod => (
                    <Card key={prod.pid} {...prod} setSubProduct={setSubProduct}/>
                ))}
            </div>
        </div>
    )

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