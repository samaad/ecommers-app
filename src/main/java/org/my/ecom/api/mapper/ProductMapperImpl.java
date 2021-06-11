package org.my.ecom.api.mapper;

import org.my.ecom.api.modal.Product;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductMapperImpl implements ProductMapper{
    @Override
    public Product entityToUi(org.my.ecom.entities.Product products) {
        if(products == null){
            return null;
        }
       return Product.builder()
                .cid(products.getCid())
                .pid(products.getPid())
                .productDescription(products.getProductDescription())
                .productName(products.getProductName())
                .productPrice(products.getProductPrice())
                .personId(products.getPersonId())
                .productImg(products.getProductImg())
//                  .subProducts(products.getSubProducts())
                .build();

    }

    @Override
    public org.my.ecom.entities.Product uiToEntity(Product products) {
        if(products == null){
            return null;
        }

       return org.my.ecom.entities.Product.builder()
                .cid(products.getCid())
                .pid(products.getPid())
                .productDescription(products.getProductDescription())
                .productName(products.getProductName())
                .productPrice(products.getProductPrice())
                .personId(products.getPersonId())
                .productImg(products.getProductImg())
//                .subProducts(products.getSubProducts())
                .build();
    }

    @Override
    public Set<Product> entityToUiList(Set<org.my.ecom.entities.Product> products) {
        if(products.isEmpty()){
            return null;
        }

        return products.stream().map(per -> {
            return Product.builder()
                    .cid(per.getCid())
                    .pid(per.getPid())
                    .productDescription(per.getProductDescription())
                    .productName(per.getProductName())
                    .productPrice(per.getProductPrice())
                    .personId(per.getPersonId())
                    .productImg(per.getProductImg())
//                    .subProducts(per.getSubProducts())
                    .build();
        }).collect(Collectors.toSet());
    }

    @Override
    public Set<org.my.ecom.entities.Product> uiToEntityList(Set<Product> products) {
        if(products.isEmpty()){
            return null;
        }

        return products.stream().map(per -> {
            return org.my.ecom.entities.Product.builder()
                    .cid(per.getCid())
                    .pid(per.getPid())
                    .productDescription(per.getProductDescription())
                    .productName(per.getProductName())
                    .productPrice(per.getProductPrice())
                    .personId(per.getPersonId())
                    .productImg(per.getProductImg())
//                    .subProducts(per.getSubProducts())
                    .build();
        }).collect(Collectors.toSet());
    }
}
