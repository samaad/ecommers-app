import React from "react";
import PropTypes from "prop-types";

const Category = (props) => {

    const {
        menuOptions,
        setSubProduct
    } = props

    // const mensCategory = menuOptions.find(option => option.gender == "Men")
    //
    // const {
    //     categories
    // } = mensCategory

    const showSubProduct = (e, product) => {
        e.preventDefault();
        setSubProduct(product);
    }

    return (
            <div className="home">
                    <div className="home__row">
                        {menuOptions && menuOptions.map(person => (
                            person && person.categories.map(cat => (
                                <div key={cat.cid} className="product">
                                    <div className="product__info">
                                        <p>{person.gender + "  " + cat.categoryName}</p>
                                    </div>
                                    <img src={cat.categoryImg} alt=""/>
                                    <button
                                        onClick={e => showSubProduct(e, cat.products)}>show subproduct
                                    </button>
                                </div>
                            ))
                        ))}
                </div>
            </div>


    );
};
Category.propTypes = {
    title: PropTypes.string,
};
export default Category;