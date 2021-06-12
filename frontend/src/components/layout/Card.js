import React from "react";
import PropTypes from "prop-types";

const Card = ({ cid, personId, pid, productDescription, productName, productPrice, productImg, subProducts, setSubProduct }) => {


    const showSubProduct = (e, subProduct) => {
        e.preventDefault();
        setSubProduct(subProduct);
    }

    const isSubProduct = subProducts && subProducts.length > 0;

    const getContent = () => (
        <div key={pid} className="product" >
            <div className="product__info">
                <p>{productName}</p>
                { productPrice ||
                < p className="product__price">
                    <small>$</small>
                    <strong>{productPrice}</strong>
                </p>
                }
            </div>
            <img src={productImg} alt=""/>
            <div>{productDescription}</div>
            <button onClick={e => showSubProduct(e, subProducts)}>{isSubProduct ? 'Show subProduct' :'Add to basket'}</button>
        </div>
    )

    return (
        <div className="home">
            <div className="home__row">
                { pid ? getContent() : <span>No Content</span> }
            </div>
        </div>

    );
};
Card.propTypes = {
    title: PropTypes.string,
};
export default Card;