package org.my.ecom.controller;

import lombok.RequiredArgsConstructor;
import org.my.ecom.api.modal.Person;
import org.my.ecom.api.modal.Product;
import org.my.ecom.service.EcomService;
import org.my.ecom.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ecom/api")
@RequiredArgsConstructor
public class EcomController {

    private final EcomService ecomService;
    private final PersonService personService;

    @GetMapping("/ecom")
    @Deprecated
    public ResponseEntity<List<Person>> getAllPersonDetails(){
        List<Person> allPerson = personService.getEcomDetails();
        return new ResponseEntity<>(allPerson, HttpStatus.OK);
    }
    @Deprecated
    @GetMapping("/custom")
    public ResponseEntity<List<Person>> getAllPersonCustom(){
        List<Person> allPerson = personService.getEcomDetailsWithQuery();
        return new ResponseEntity<>(allPerson, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<Product>> getAllProductsDetails(){
        Set<Product> allProduct = ecomService.findAllProduct();
        return new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    @GetMapping("{/productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long id){
        Product product1 = ecomService.findByIdProduct(id);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }


    /**
     * This is used for saving product
     * @param product
     * @return
     */
    @PostMapping
    public ResponseEntity<Set<Product>> saveProducts(@RequestBody List<Product> product){
        Set<Product> product1 = ecomService.saveProducts(product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }


    @PutMapping("{/productId}")
    public ResponseEntity<Product> updateProductsDetails(@RequestBody Product product){
        Product product1 = ecomService.updateProduct(product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @DeleteMapping("{/productId}")
    public ResponseEntity<Void> removeProductsDetails(@PathVariable("productId") Long id){
         ecomService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
