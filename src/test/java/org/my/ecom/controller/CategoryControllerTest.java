package org.my.ecom.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.my.ecom.api.modal.Categories;
import org.my.ecom.api.modal.Person;
import org.my.ecom.api.modal.Product;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @Test
    void getAllPersonDetails() throws Exception {

        List<Product> products = Arrays.asList(new Product(1l, "Addidas", "Sprots Brand", new BigDecimal(123.55), 2l, 3l, "image",  null));
        Set<Product> productset = new HashSet<>(products);

        List<Product> products1 = Arrays.asList(new Product(1l, "Casual Shirt", "Party Ware", new BigDecimal(123.55), 2l, 3l, "Image.png", null));
        Set<Product> productset1 = new HashSet<>(products1);
        List<Categories> categories = Arrays.asList(
                new Categories(1l, "Nike", "Sport", productset),
                new Categories(2l, "H&M", "clothing brand",productset1)
        );

        Set<Categories> categorieset = new HashSet<>(categories);

        List<Person> persons = Arrays.asList(new Person(1l, "male", categorieset));
        when(personService.getEcomDetails()).thenReturn(persons);
        RequestBuilder request = MockMvcRequestBuilders.get("/person/api");
//        MvcResult mvcResult = mockMvc.perform(request).andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"gender\":\"male\",\"categories\":[{\"cid\":1,\"categoryName\":\"Nike\",\"categoryImg\":\"Sport\",\"products\":[{\"pid\":1,\"productName\":\"Addidas\",\"productDescription\":\"Sprots Brand\",\"productPrice\":123.5499999999999971578290569595992565155029296875,\"cid\":2,\"personId\":3,\"productImg\":\"image\",\"subProducts\":null}]},{\"cid\":2,\"categoryName\":\"H&M\",\"categoryImg\":\"clothing brand\",\"products\":[{\"pid\":1,\"productName\":\"Casual Shirt\",\"productDescription\":\"Party Ware\",\"productPrice\":123.5499999999999971578290569595992565155029296875,\"cid\":2,\"personId\":3,\"productImg\":\"Image.png\",\"subProducts\":null}]}]}]"));
    }

    @Test
    void savePerson() throws Exception {
        List<org.my.ecom.api.modal.Categories> categoriel = Arrays.asList(
                new org.my.ecom.api.modal.Categories(1l, "Nike", "Sport", null)
        );
        Set<org.my.ecom.api.modal.Categories> categoriesModel = new HashSet<>(categoriel);

        Person person = new Person(2l, "Female", categoriesModel);

        String json = "{\"id\":2,\"gender\":\"Female\",\"categories\":[{\"cid\":1,\"categoryName\":\"Nike\",\"categoryImg\":\"Sport\",\"products\":null}]}";


        when(personService.savePersonWithCategory(Mockito.any())).thenReturn(person);
        RequestBuilder request = MockMvcRequestBuilders.post("/person/api").contentType(
                MediaType.APPLICATION_JSON).content(json);;
        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }
}