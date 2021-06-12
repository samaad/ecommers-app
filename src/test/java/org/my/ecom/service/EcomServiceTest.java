package org.my.ecom.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.my.ecom.api.modal.Product;
import org.my.ecom.entities.SubProduct;
import org.my.ecom.repository.ProductRepository;
import org.my.ecom.repository.SubProductReposity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class EcomServiceTest {

    @InjectMocks
    EcomService service;

    @Mock
    ProductRepository productRepository;
    @Mock
    SubProductReposity subProductReposity;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveProductWithoutSubproduct() {

        Product product = Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sprots Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .subProducts(new HashSet<>())
                .build();

        org.my.ecom.entities.Product endtProduct = new org.my.ecom.entities.Product(1l, "Addidas", "Sprots Brand", new BigDecimal(123.55),"http://addidas.img", 2l, 3l);
        when(productRepository.save(Mockito.any())).thenReturn(endtProduct);

//        Product product1 = service.saveProduct(Mockito.any(Product.class));
        Product product1 = service.saveProduct(product);

        assertEquals(product1.getPid(), 1l);
        verify(productRepository, times(1)).save(Mockito.any());
    }

    @Test
    void saveProductWithSubproduct() {

        List<SubProduct> subProduct = Arrays.asList(
                new SubProduct(1l, "Jogging shoe", "good for running", "http://runningimage.png", new BigDecimal(100.2))
        );
        Set<SubProduct> subProducts = new HashSet<>(subProduct);
        Product product = Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sprots Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .subProducts(subProducts)
                .build();

        org.my.ecom.entities.Product endtProduct = org.my.ecom.entities.Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sprots Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .build();

        when(productRepository.save(Mockito.any())).thenReturn(endtProduct);
        when(subProductReposity.saveAll(Mockito.any())).thenReturn(subProduct);

//        Product product1 = service.saveProduct(Mockito.any(Product.class));
        Product product1 = service.saveProduct(product);
        assertEquals(1, product1.getSubProducts().size());
        verify(productRepository, times(1)).save(Mockito.any());
    }

    @Test
    void saveProductWithSubproductAndChildSubProduct() {

        SubProduct subProduct1 = new SubProduct(1l, "casual shoe", "new to brand", "http://runningimage.png", new BigDecimal(100.2));
        SubProduct subProduct2 = new SubProduct(1l, "canvas", "stylies", "http://runningimage.png", new BigDecimal(100.2));
        subProduct2.setParentSubProduct(subProduct1);
        subProduct1.setParentSubProduct(subProduct1);
        List<SubProduct> childSubProduct = Arrays.asList(subProduct2);
        subProduct1.setChildSubProduct(new HashSet<>(childSubProduct));

        List<SubProduct> subProduct = Arrays.asList(
                new SubProduct(1l, "Jogging shoe", "good for running", "http://runningimage.png", new BigDecimal(100.2)),
                subProduct1
        );
        Set<SubProduct> subProducts = new HashSet<>(subProduct);
        Product product = Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sprots Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .subProducts(subProducts)
                .build();


        org.my.ecom.entities.Product endtProduct = org.my.ecom.entities.Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sprots Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .build();
        when(productRepository.save(Mockito.any())).thenReturn(endtProduct);
        when(subProductReposity.saveAll(Mockito.any())).thenReturn(subProduct);

//        Product product1 = service.saveProduct(Mockito.any(Product.class));
        Product product1 = service.saveProduct(product);
        assertEquals(2, product1.getSubProducts().size());
        verify(productRepository, times(1)).save(Mockito.any());
    }

    @Test
    void findAllProductWithData() {
        org.my.ecom.entities.Product productBuilder = org.my.ecom.entities.Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sprots Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .build();
        List<org.my.ecom.entities.Product> endtProduct = Arrays.asList(
                productBuilder
        );
        when(productRepository.findAll()).thenReturn(endtProduct);

        Set<Product> product1 = service.findAllProduct();

        assertEquals(1, product1.size());
        assertEquals("Addidas", product1.iterator().next().getProductName());
    }

    @Test
    void findAllProductWithoutData() {

        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        Set<Product> product1 = service.findAllProduct();

        assertEquals(null, product1);
    }

    @Test
    void updateProduct() {
        org.my.ecom.entities.Product endtProduct = org.my.ecom.entities.Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sports Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .build();

        when(productRepository.save(Mockito.any())).thenReturn(endtProduct);
        Product product1 = service.updateProduct(Mockito.any());

        assertEquals("Sports Brand", product1.getProductDescription());
        verify(productRepository, times(1)).save(Mockito.any());
    }

    @Test
    void findByIdProduct() {
        org.my.ecom.entities.Product endtProduct = org.my.ecom.entities.Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sports Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .build();
        when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(endtProduct));

        Product byIdProduct = service.findByIdProduct(Mockito.anyLong());
        assertEquals("Sports Brand", byIdProduct.getProductDescription());
    }

    @Test
    void findByIdProductWithoutId() {
        when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Product byIdProduct = service.findByIdProduct(Mockito.anyLong());
        assertEquals(null, byIdProduct);
    }

    @Test
    void removeProduct() {
        List<SubProduct> subProduct = Arrays.asList(
                new SubProduct(1l, "Jogging shoe", "good for running", "http://runningimage.png", new BigDecimal(100.2))
        );
        org.my.ecom.entities.Product endtProduct = org.my.ecom.entities.Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sprots Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .build();
        when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(endtProduct));
        doNothing().when(productRepository).delete(Mockito.any());
        when(subProductReposity.findByPid(Mockito.any())).thenReturn(subProduct);
        doNothing().when(subProductReposity).deleteAll(Mockito.any());
        productRepository.delete(Mockito.any());
        subProductReposity.delete(Mockito.any());
        verify(productRepository, times(1)).delete(Mockito.any());
        verify(subProductReposity, times(1)).delete(Mockito.any());

    }

    @Test
    public void removeProductUsingArgumentCaptor() {

        ArgumentCaptor<org.my.ecom.entities.Product> idCapture = ArgumentCaptor.forClass(org.my.ecom.entities.Product.class);
        doNothing().when(productRepository).delete(idCapture.capture());
        when(subProductReposity.findByPid(Mockito.any())).thenReturn(new ArrayList<>());
        org.my.ecom.entities.Product product = org.my.ecom.entities.Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sprots Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .build();
        productRepository.delete(product);

        assertEquals(product.getPid(), idCapture.getValue().getPid());
    }

    @Test
    void saveProducts() {

        Product product = Product.builder().pid(1l)
                .productName("Addidas")
                .productDescription("Sports Brand")
                .productImg("http://addidas.img")
                .personId(2l)
                .cid(1l)
                .productPrice(new BigDecimal(123.55))
                .subProducts(new HashSet<>())
                .build();


        List<Product> products= Arrays.asList(product);
        org.my.ecom.entities.Product endtProduct = org.my.ecom.entities.Product.builder()
                .pid(1l)
                .personId(2l)
                .cid(1l)
                .productDescription("Sprots Brand")
                .productImg("http://addidas.img")
                .productName("Addidas")
                .productPrice(new BigDecimal(123.55))
                .build();
        List<org.my.ecom.entities.Product> enProducts= Arrays.asList(endtProduct);
        when(productRepository.saveAll(Mockito.any())).thenReturn(enProducts);

        when(productRepository.save(Mockito.any())).thenReturn(endtProduct);

        Set<Product> product1 = service.saveProducts(products);

        assertEquals(product1.iterator().next().getPid(), 1l);
        verify(productRepository, times(1)).save(Mockito.any());
    }
}