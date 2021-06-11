package org.my.ecom.controller;

import lombok.RequiredArgsConstructor;
import org.my.ecom.api.modal.Person;
import org.my.ecom.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person/api")
public class CategoryController {

    private final PersonService personService;

    /**
     * Returns list of all menu options including person->categories->products->subproducts
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<Person>> getAllPersonDetails(){
        List<Person> allPerson = personService.getEcomDetails();
        return new ResponseEntity<>(allPerson, HttpStatus.OK);
    }

    /**
     * Save Person and it's related Category Items
     * @param person
     * @return
     */
    @PostMapping()
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        Person allPerson = personService.savePersonWithCategory(person);
        return new ResponseEntity<>(allPerson, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/category/{categoryId}")
    public ResponseEntity<Void> removeCategory(@PathVariable("categoryId") Long id){
        personService.removeCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
