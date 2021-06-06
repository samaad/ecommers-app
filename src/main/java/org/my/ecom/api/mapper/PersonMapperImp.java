package org.my.ecom.api.mapper;

import org.my.ecom.api.modal.Person;
import org.my.ecom.entities.Categories;
import org.my.ecom.entities.Persons;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonMapperImp implements PersonMapper {

    @Override
    public Person entityToUi(Persons persons, boolean isCategory) {
        if(persons == null){
            return null;
        }
        Set<Categories> categories = persons.getCategories();
        Set<org.my.ecom.api.modal.Categories> categories1 = uiToEntityCategories(categories);
        return Person.builder().id(persons.getId())
                .gender(persons.getGender())
                .Categories(isCategory ? categories1 : new HashSet<>()).build();
    }

    @Override
    public Persons uiToEntity(Person person, boolean isCategory) {
        if(person == null){
            return null;
        }
        Set<org.my.ecom.api.modal.Categories> categories = person.getCategories();
        Set<Categories> categories1 = entityToUiCategories(categories);
        return Persons.builder().id(person.getId())
                .gender(person.getGender())
                .categories(isCategory ? categories1 : new HashSet<>())
                .build();
    }

    @Override
    public List<Person> entityToUiList(List<Persons> persons, boolean isCategory) {
        if(persons.isEmpty()){
            return null;
        }

        return persons.stream().map(per -> {
            Set<Categories> categories = per.getCategories();
            Set<org.my.ecom.api.modal.Categories> categories1 = uiToEntityCategories(categories);
            return Person.builder().id(per.getId())
                    .gender(per.getGender())
                    .Categories(isCategory ? categories1 : new HashSet<>())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<Persons> uiToEntityList(List<Person> person,  boolean isCategory) {
        if(person.isEmpty()){
            return null;
        }

        return person.stream().map(per -> {
            Set<org.my.ecom.api.modal.Categories> categories = per.getCategories();
            Set<Categories> categories1 = entityToUiCategories(categories);
            return Persons.builder().id(per.getId())
                    .gender(per.getGender())
                    .categories(isCategory ? categories1 : new HashSet<>()).build();
        }).collect(Collectors.toList());
    }

    public Set<org.my.ecom.api.modal.Categories> uiToEntityCategories(Set<Categories> categories){
        if(categories.isEmpty()){
            return null;
        }
        return categories.stream().map(per -> {
            return org.my.ecom.api.modal.Categories.builder()
                    .categoryName(per.getCategoryName())
                    .cid(per.getCid())
                    .categoryImg(per.getCategoryImg())
                    .build();
        }).collect(Collectors.toSet());

    }

    public Set<Categories> entityToUiCategories(Set<org.my.ecom.api.modal.Categories> categories){
        if(categories.isEmpty()){
            return null;
        }
        return categories.stream().map(per -> {
            return Categories.builder()
                    .categoryName(per.getCategoryName())
                    .cid(per.getCid())
                    .categoryImg(per.getCategoryImg())
                    .build();
        }).collect(Collectors.toSet());

    }
}
