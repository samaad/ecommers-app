# Appliction Docs

### Backend Reference
Spring Boot Hibernate postgresql 

* [http://localhost:8111/person/api](http://localhost:8111/person/api) POST

When Id is provided then update takes place if Id is not provided then insertion happens

        {
            "id": 2,
            "gender": "Women",
            "categories": [
                {
                    "categoryName": "Cloths",
                    "products": null
                },
                {
                    "cid": 1,
                    "categoryName": "Alod",
                    "products": null
                }
            ]
        }

* [http://localhost:8111/person/api](http://localhost:8111/person/api) GET



    [
        {
            "id": 1,
            "gender": "Men",
            "categories": [
                {
                    "cid": 2,
                    "categoryName": "H&M",
                    "products": null
                },
                {
                    "cid": 1,
                    "categoryName": "Alod",
                    "products": [
                        {
                            "pid": 2,
                            "productName": "Branded shoes",
                            "productDescription": "Sport Shoes",
                            "productPrice": 100.00,
                            "cid": 1,
                            "personId": 1,
                            "subProducts": [
                                {
                                    "spid": 4,
                                    "subproducName": "Casuals",
                                    "description": null,
                                    "parentSubProduct": null,
                                    "childSubProduct": [],
                                    "pid": 2
                                },
                                {
                                    "spid": 5,
                                    "subproducName": Formals,
                                    "description": null,
                                    "parentSubProduct": null,
                                    "childSubProduct": [],
                                    "pid": 2
                                }
                            ]
                        }
                    ]
                }
            ]
        },
        {
            "id": 2,
            "gender": "Women",
            "categories": [
                {
                    "cid": 3,
                    "categoryName": "Cloths",
                    "products": [
                        {
                            "pid": 1,
                            "productName": "Jimmy Choo",
                            "productDescription": "Branded cloths",
                            "productPrice": 100.00,
                            "cid": 3,
                            "personId": 2,
                            "subProducts": [
                                {
                                    "spid": 1,
                                    "subproducName": "Short shirts",
                                    "description": null,
                                    "parentSubProduct": null,
                                    "childSubProduct": [],
                                    "pid": 1
                                },
                                {
                                    "spid": 2,
                                    "subproducName": "T-Shirts",
                                    "description": null,
                                    "parentSubProduct": null,
                                    "childSubProduct": [],
                                    "pid": 1
                                }
                            ]
                        }
                    ]
                },
                {
                    "cid": 1,
                    "categoryName": "Alod",
                    "products": null
                }
            ]
        }
    ]


* [http://localhost:8111/ecom/api](http://localhost:8111/ecom/api) GET

Add List of products and sub products

     [{
            "pid": 2,
            "productName": "Branded shoes",
            "productDescription": "Sport Shoes",
            "productPrice": 100,
            "cid": 1,
            "personId": 1,
            "subProducts": [{
                "subproducName": "Jogging shoe",
                "parentSubProduct": {
                    "subproducName": "Jogging shoe for morning walk"
                }
            }]
        }
    ]


This delete's the product with specified product id and it corresponding sub products
* [http://localhost:8111/ecom/api/{productId}](http://localhost:8111/ecom/api/{productId}) DELETE


This delete's the category with specified category id and it corresponding products and sub products too
* [http://localhost:8111/person/api/remove/category/{CategoryId}](http://localhost:8111/person/api/remove/category/{CategoryId}) DELETE




### Frontend Reference
React Axios 

Before Running the application execute this command for Building the react application this will build bundle.js file

    npm run watch 

* [http://localhost:8111/ecom/api](http://localhost:8111)

This link will return the React page with the UI