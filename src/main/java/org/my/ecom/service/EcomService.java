package org.my.ecom.service;

import lombok.RequiredArgsConstructor;
import org.my.ecom.api.mapper.ProductMapperImpl;
import org.my.ecom.api.modal.Product;
import org.my.ecom.entities.SubProduct;
import org.my.ecom.repository.SubProductReposity;
import org.my.ecom.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class EcomService {

    private final ProductRepository productRepository;
    private final SubProductReposity subProductReposity;

    ProductMapperImpl productMapper = new ProductMapperImpl();
    public Product saveProduct(Product product){
        org.my.ecom.entities.Product enProduct = productMapper.uiToEntity(product);
        org.my.ecom.entities.Product saveProduct = productRepository.save(enProduct);
        List<SubProduct> subProduct = saveSubproduct(saveProduct.getPid(), product);
        product = productMapper.entityToUi(saveProduct);
        product.setSubProducts(new HashSet<>(subProduct));
        return product;
    }

    private List<SubProduct> saveSubproduct(Long entProduct, Product product) {
        Set<SubProduct> subProducts = product.getSubProducts();
        for(SubProduct subProduct: subProducts){
            subProduct.setPid(entProduct);
        }
        return subProductReposity.saveAll(subProducts);
    }

    public Set<Product> findAllProduct(){
        List<org.my.ecom.entities.Product> all = productRepository.findAll();
        HashSet<org.my.ecom.entities.Product> products = new HashSet<>(all);
        Set<Product> products1 = productMapper.entityToUiList(products);
        return products1;
    }

    public Product updateProduct(Product product) {
        org.my.ecom.entities.Product enProduct = productMapper.uiToEntity(product);
        org.my.ecom.entities.Product save = productRepository.save(enProduct);
        return productMapper.entityToUi(save);
    }

    public Product findByIdProduct(Long id) {
        Optional<org.my.ecom.entities.Product> product = productRepository.findById(id);
        return productMapper.entityToUi(product.get());
    }

    public void removeProduct(Long id) {
        Product product = findByIdProduct(id);
        org.my.ecom.entities.Product enProduct = productMapper.uiToEntity(product);
        productRepository.delete(enProduct);
    }

    public Set<Product> saveProducts(List<Product> products) {
        List<Product> result = new ArrayList<>();

//      Iterar and save the first product and then pass it's id to the next one

        for(Product product: products){
            Product product1 = saveProduct(product);
            result.add(product1);
        }

        HashSet<Product> hashProduct = new HashSet<>(result);
        Set<org.my.ecom.entities.Product> products1 = productMapper.uiToEntityList(hashProduct);

        List<org.my.ecom.entities.Product> products2 = productRepository.saveAll(products1);
        HashSet<org.my.ecom.entities.Product> saveProducts = new HashSet<>(products2);
        return productMapper.entityToUiList(saveProducts);
    }
}
