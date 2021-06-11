import React, { useState, useEffect } from "react";
import ReactDOM from "react-dom";
import Header from "../components/header/Header";
import MenuBar from "../components/Menu/MenuBar";
import Category from "../components/layout/Category";
import axios from "axios";
import SubProducts from "../components/layout/SubProducts";
import isEmpty from 'lodash/isEmpty';

const App = () => {
    const [menuOptions, setmenuOptions] = useState([]);
    const [selectCategory, setSelectCategory] = useState({});
    const [subProduct, setSubProduct] = useState({});
    useEffect(() => {
        axios.get("/person/api").then((response) => {
            setmenuOptions(response.data);
        });
    }, []);

    return (
        <div>
        <Header title="Ecommerce Web app" />
        <MenuBar key={'render'} menuOptions={menuOptions} setSelectCategory={setSubProduct} />
            {
                isEmpty(subProduct) && !isEmpty(menuOptions) &&
                <Category menuOptions={menuOptions} setSubProduct={setSubProduct}/>
            }
            { !isEmpty(subProduct) &&
                <SubProducts product ={subProduct}/>

            }
    </div>
);
};

export default App;

ReactDOM.render(<App />, document.querySelector("#app"));
