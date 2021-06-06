import React from "react";
import PropTypes from "prop-types";

const SubProducts = (subProduct) => {

    const showSubProduct = () => {

    }

    console.log(subProduct);

    // const isSubProduct = products && products.subProducts && products.subProducts.length > 0;
    // const { cid, personId, pid, productDescription, productName, productPrice, productImg, subProducts} = products;

    // const getContent = () => (
    //     products && products.map(({ description, pid, productImg, subproducName, spid }) => (
    //         <div key={pid} className="product" >
    //             <div className="product__info">
    //                 <p>{subproducName}</p>
    //             </div>
    //             <img src={productImg} alt=""/>
    //             <div>{description}</div>
    //             <button onclick={() => showSubProduct()}>{'Add to basket'}</button>
    //         </div>
    //     ))
    // )

    return (
        <div className="home">
            <div className="home__row">
                <span>No Content</span>
            </div>
        </div>

    );
};
SubProducts.propTypes = {
    title: PropTypes.string,
};
export default SubProducts;