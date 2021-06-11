package org.my.ecom.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.my.ecom.api.modal.Person;
import org.my.ecom.api.modal.Product;
import org.my.ecom.service.EcomService;
import org.my.ecom.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EcomController.class)
class EcomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EcomService ecomService;
    @MockBean
    private PersonService personService;



    @Test
    void getAllPersonDetails() throws Exception {
        List<Person> persons = Arrays.asList(new Person(1l, "male", new HashSet<>()));
        when(personService.getEcomDetails()).thenReturn(persons);
        RequestBuilder request = MockMvcRequestBuilders.get("/ecom/api/ecom");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"gender\":\"male\",\"categories\":[]}]"));
    }

    @Test
    void getAllProductsDetails() throws Exception {
        List<Product> products = Arrays.asList(new Product(1l, "Addidas", "Sprots Brand", new BigDecimal(123.55), 2l, 3l, "http://addidas.img", new HashSet<>()));
        Set<Product> allProduct = new HashSet<>(products);
        when(ecomService.findAllProduct()).thenReturn(allProduct);
        RequestBuilder request = MockMvcRequestBuilders.get("/ecom/api");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"pid\":1,\"productName\":\"Addidas\",\"productDescription\":\"Sprots Brand\",\"productPrice\":123.5499999999999971578290569595992565155029296875,\"cid\":2,\"personId\":3,\"productImg\":\"http://addidas.img\",\"subProducts\":[]}]"));
    }

    @Test
    void saveProducts() throws Exception {
        String json = "[{\"pid\":1,\"productName\":\"Addidas\",\"productDescription\":\"Sprots Brand\",\"productPrice\":123.5499999999999971578290569595992565155029296875,\"cid\":2,\"personId\":3,\"productImg\":\"http://addidas.img\",\"subProducts\":[]}]";
        List<Product> products = Arrays.asList(new Product(1l, "Addidas", "Sprots Brand", new BigDecimal(123.55), 2l, 3l, "http://addidas.img", new HashSet<>()));
        Set<Product> allProduct = new HashSet<>(products);
        when(ecomService.saveProducts(Matchers.anyList())).thenReturn(allProduct);
        RequestBuilder request = MockMvcRequestBuilders.post("/ecom/api").contentType(
                MediaType.APPLICATION_JSON).content(json);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }

    @Test
    void updateProductsDetails() {
    }

    @Test
    void removeProductsDetails() {
    }
}