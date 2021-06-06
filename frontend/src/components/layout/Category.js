import React from "react";
import PropTypes from "prop-types";

const Category = ({ products, setSubProduct }) => {

    const showSubProduct = (subProduct) => {
        setSubProduct(subProduct);
    }

    const isSubProduct = products && products.subProducts && products.subProducts.length > 0;
    // const { cid, personId, pid, productDescription, productName, productPrice, productImg, subProducts} = products;

    const getContent = () => (
        products && products.map(({ cid, personId, pid, productDescription, productName, productPrice, productImg, subProducts}) => (
            <div key={pid} className="product" >
                <div className="product__info">
                    <p>{productName}</p>
                    { subProducts ||
                    < p className="product__price">
                        <small>$</small>
                        <strong>{productPrice}</strong>
                        </p>
                    }
                </div>
                <img src={productImg} alt=""/>
                <div>{productDescription}</div>
                <button onclick={() => showSubProduct(subProducts)}>{isSubProduct ? 'Show subProduct' :'Add to basket'}</button>
            </div>
        ))
    )

    return (
        <div className="home">
            <div className="home__row">
                { products ? getContent() : <span>No Content</span> }
            </div>
        </div>

    );
};
Category.propTypes = {
    title: PropTypes.string,
};
export default Category;