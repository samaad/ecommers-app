package org.my.ecom.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.my.ecom.api.modal.Person;
import org.my.ecom.entities.Categories;
import org.my.ecom.entities.Persons;
import org.my.ecom.entities.Product;
import org.my.ecom.entities.SubProduct;
import org.my.ecom.repository.CategoriesRepository;
import org.my.ecom.repository.PersonRepository;
import org.my.ecom.repository.ProductRepository;
import org.my.ecom.repository.SubProductReposity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    CategoriesRepository categoriesRepository;
    @Mock
    SubProductReposity subProductReposity;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEcomDetails() {
        List<Categories> categories = Arrays.asList(
                new Categories(1l, "Nike", "Sport"),
                new Categories(2l, "H&M", "clothing brand")
        );

        Set<Categories> categorieset = new HashSet<>(categories);
        List<Persons> persons = Arrays.asList(
            new Persons(1l, "Male", categorieset),
            new Persons(2l, "Female", categorieset)
        );

        when(personRepository.findAll()).thenReturn(persons);
        when(productRepository.findByCidAndPersonId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(new HashSet<>());

        List<Person> ecomDetails = personService.getEcomDetails();
        assertEquals(2l, ecomDetails.size());
        assertEquals(2l, ecomDetails.get(0).getCategories().size());
        assertEquals("Nike", ecomDetails.get(0).getCategories().iterator().next().getCategoryName());
        verify(productRepository, times(4)).findByCidAndPersonId(Mockito.anyLong(), Mockito.anyLong());
    }

    @Test
    void getEcomDetailsWithooutCategories(){

        Set<Categories> categorieset = new HashSet<>();
        List<Persons> persons = Arrays.asList(
                new Persons(1l, "Male", categorieset),
                new Persons(2l, "Female", categorieset)
        );

        when(personRepository.findAll()).thenReturn(persons);

        List<Person> ecomDetails = personService.getEcomDetails();
        assertEquals(2l, ecomDetails.size());
        assertEquals(null, ecomDetails.get(0).getCategories());

    }

    @Test
    void getEcomDetailsUpdateingExistingList() {
        List<Categories> categories = Arrays.asList(
                new Categories(1l, "Nike", "Sport"),
                new Categories(2l, "H&M", "clothing brand")
        );

        Set<Categories> categorieset = new HashSet<>(categories);
        List<Persons> persons = Arrays.asList(
                new Persons(1l, "Male", categorieset),
                new Persons(2l, "Female", categorieset)
        );


        List<Product> products = Arrays.asList(
          new Product(1l, "Zara jacket", "winter jackets", new BigDecimal(12.32), "image.png", 1l, 1l),
          new Product(1l, "Zara shirts", "winter jackets", new BigDecimal(12.32), "image.png", 1l, 2l)
        );
        Set<Product> productset = new HashSet<>(products);

        List<SubProduct> subProducts = Arrays.asList(
                new SubProduct(1l, "jackets", "jackets for all seasons", "subImage.png")
        );
        when(personRepository.findAll()).thenReturn(persons);
        when(productRepository.findByCidAndPersonId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(productset);
        when(subProductReposity.findByPid(Mockito.anyLong())).thenReturn(subProducts);

        List<Person> ecomDetails = personService.getEcomDetails();
        Set<org.my.ecom.api.modal.Product> products1 = ecomDetails.get(0).getCategories().iterator().next().getProducts();
        assertEquals(2l, ecomDetails.size());
        assertEquals(2l, ecomDetails.get(0).getCategories().size());
        assertEquals("Nike", ecomDetails.get(0).getCategories().iterator().next().getCategoryName());
        assertEquals("Zara jacket", products1.iterator().next().getProductName());

        verify(productRepository, times(4)).findByCidAndPersonId(Mockito.anyLong(), Mockito.anyLong());
        verify(subProductReposity, times(8)).findByPid(Mockito.anyLong());
    }

    @Test
    void getEcomDetailsWithQuery() {
        List<Person> persons = Arrays.asList(
                new Person(1l, "Male", null),
                new Person(2l, "Female", null)
        );
        when(personRepository.getAllProductList()).thenReturn(persons);

        List<Person> ecomDetailsWithQuery = personService.getEcomDetailsWithQuery();
        assertEquals(2l, ecomDetailsWithQuery.size());
        assertEquals("Female", ecomDetailsWithQuery.get(1).getGender());
        verify(personRepository, times(1)).getAllProductList();
    }

    @Test
    void savePersonWithCategoryWithPersonId() {
        List<Categories> categories = Arrays.asList(
                new Categories(1l, "Nike", "Sport"),
                new Categories(2l, "H&M", "clothing brand")
        );

        Set<Categories> categorieset = new HashSet<>(categories);

        Persons entPerson = new Persons(null, "Male", categorieset);

        List<org.my.ecom.api.modal.Categories> categoriel = Arrays.asList(
                new org.my.ecom.api.modal.Categories(1l, "Nike", "Sport", null),
                new org.my.ecom.api.modal.Categories(2l, "H&M", "clothing brand", null)
        );
        Set<org.my.ecom.api.modal.Categories> categoriesModel = new HashSet<>(categoriel);

        Person person = new Person(null, "Male", categoriesModel);


        when(categoriesRepository.saveAll(Mockito.any())).thenReturn(categories);
        when(personRepository.save(Mockito.any())).thenReturn(entPerson);

        Person personRes = personService.savePersonWithCategory(person);
        assertEquals("Male", personRes.getGender());
        verify(personRepository, times(1)).save(Mockito.any());
        verify(categoriesRepository, times(1)).saveAll(Mockito.any());
    }

    @Test
    void savePersonWithCategory() {
        List<Categories> categories = Arrays.asList(
                new Categories(2l, "H&M", "clothing brand")
        );

        Set<Categories> categorieset = new HashSet<>(categories);

        List<org.my.ecom.api.modal.Categories> categoriel = Arrays.asList(
                new org.my.ecom.api.modal.Categories(1l, "Nike", "Sport", null)
        );
        Set<org.my.ecom.api.modal.Categories> categoriesModel = new HashSet<>(categoriel);

        Person person = new Person(2l, "Female", categoriesModel);

        Persons entPerson = new Persons(2l, "Female", categorieset);

        when(categoriesRepository.getCategoriesByPersonId(Mockito.anyLong())).thenReturn(categories);
        when(personRepository.save(Mockito.any())).thenReturn(entPerson);
        Person personRes = personService.savePersonWithCategory(person);
        assertEquals("Female", personRes.getGender());
        verify(personRepository, times(1)).save(Mockito.any());
        verify(categoriesRepository, times(1)).getCategoriesByPersonId(Mockito.anyLong());
    }

    @Test
    void removeCategoryWithNotCategory() {
        when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        personService.removeCategory(Mockito.anyLong());
        verify(categoriesRepository, times(1)).findById(Mockito.anyLong());
    }

    @Test
    void removeCategory() {
        Categories categories = new Categories(2l, "H&M", "clothing brand");
        when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categories));
        doNothing().when(categoriesRepository).removeCategory(Mockito.anyLong());
        doNothing().when(categoriesRepository).delete(Mockito.any());
        categoriesRepository.removeCategory(Mockito.anyLong());
        categoriesRepository.delete(Mockito.any());

        List<Product> products = Arrays.asList(
                new Product(1l, "Zara jacket", "winter jackets", new BigDecimal(12.32), "image.png", 1l, 1l),
                new Product(1l, "Zara shirts", "winter jackets", new BigDecimal(12.32), "image.png", 1l, 2l)
        );
        when(productRepository.findByCid(Mockito.anyLong())).thenReturn(products);
        doNothing().when(productRepository).deleteAll(Mockito.any());
        productRepository.deleteAll(Mockito.any());

        List<SubProduct> subProducts = Arrays.asList(
                new SubProduct(1l, "jackets", "jackets for all seasons", "subImage.png")
        );
        when(subProductReposity.findByPid(Mockito.anyLong())).thenReturn(subProducts);

        doNothing().when(subProductReposity).deleteAll(Mockito.any());
        subProductReposity.deleteAll(Mockito.any());

        personService.removeCategory(Mockito.anyLong());

        verify(categoriesRepository, times(2)).removeCategory(Mockito.anyLong());
        verify(categoriesRepository, times(2)).delete(Mockito.any());
        verify(productRepository, times(2)).deleteAll(Mockito.any());
        verify(subProductReposity, times(3)).deleteAll(Mockito.any());
    }
}