package org.my.ecom.service;

import lombok.RequiredArgsConstructor;
import org.my.ecom.api.mapper.PersonMapperImp;
import org.my.ecom.api.mapper.ProductMapperImpl;
import org.my.ecom.api.modal.Categories;
import org.my.ecom.api.modal.Person;
import org.my.ecom.entities.Persons;
import org.my.ecom.entities.Product;
import org.my.ecom.entities.SubProduct;
import org.my.ecom.repository.CategoriesRepository;
import org.my.ecom.repository.PersonRepository;
import org.my.ecom.repository.ProductRepository;
import org.my.ecom.repository.SubProductReposity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final ProductRepository productRepository;
    private final CategoriesRepository categoriesRepository;
    private final SubProductReposity subProductReposity;


    PersonMapperImp mapper = new PersonMapperImp();
    ProductMapperImpl productMapper = new ProductMapperImpl();

    /**
     * Return list of person along with it's categories -> products -> subProducts
     * @return
     */
    public List<Person> getEcomDetails(){
        List<Persons> persons = personRepository.findAll();
        List<Person> person = mapper.entityToUiList(persons, true);

        if(person != null){
            for(Person per : person){
                Set<Categories> categories = per.getCategories();
                if(categories != null && !categories.isEmpty()) {
                    for (Categories cat : categories) {
                        Set<Product> products = productRepository.findByCidAndPersonId(cat.getCid(), per.getId());
                        if (products != null && !products.isEmpty()) {
                            Set<org.my.ecom.api.modal.Product> retProducts = productMapper.entityToUiList(products);
                            if (retProducts != null) {
                                for (org.my.ecom.api.modal.Product product : retProducts) {
                                    List<SubProduct> subProducts = subProductReposity.findByPid(product.getPid());
                                    product.setSubProducts(new HashSet<>(subProducts));
                                }
                            }
                            cat.setProducts(retProducts);
                        }
                    }
                }
            }
        }
        return person;
    }

    /**
     * Try with native sql
     * @return
     */
    public List<Person> getEcomDetailsWithQuery() {
        return personRepository.getAllProductList();
    }

    /**
     * If Person Id is not provided then in that case create new Person and its categories
     * If Person Id is provided then update the person object along with it's categories
     *  same goes for categories With ans without id's
     * @param person
     * @return
     */
    public Person savePersonWithCategory(Person person) {
        Persons entPerson = mapper.uiToEntity(person, false);
        Set<org.my.ecom.entities.Categories> catMapper = mapper.entityToUiCategories(person.getCategories());
        List<org.my.ecom.entities.Categories> s;
        List<org.my.ecom.entities.Categories> catList = null;
        if(entPerson.getId() == null) {
            s = categoriesRepository.saveAll(catMapper);
            catList = s;
        }else{
            s  = categoriesRepository.getCategoriesByPersonId(entPerson.getId());
            if(!person.getCategories().isEmpty()){
                Set<org.my.ecom.entities.Categories> categories = mapper.entityToUiCategories(person.getCategories());
                catList = new ArrayList<>(categories);
                for(org.my.ecom.entities.Categories categorie : s){
                    catList.add(categorie);
                }

            }
        }
        entPerson.getCategories().addAll(catList);
            Persons savePersons = personRepository.save(entPerson);
        Person savedPerson = mapper.entityToUi(savePersons, true);
        return savedPerson;
    }

    @Transactional
    public void removeCategory(Long id) {
        Optional<org.my.ecom.entities.Categories> byId = categoriesRepository.findById(id);
        if(byId.isPresent()){
            categoriesRepository.removeCategory(byId.get().getCid());
            categoriesRepository.delete(byId.get());
            List<Product> products = productRepository.findByCid(byId.get().getCid());
            if(products !=null && !products.isEmpty()){
                productRepository.deleteAll(products);
                for(Product product : products){
                    List<SubProduct> subProduct = subProductReposity.findByPid(product.getPid());
                    if(subProduct !=null && !subProduct.isEmpty()){
                        subProductReposity.deleteAll(subProduct);
                    }
                }
            }
        }
    }
}
