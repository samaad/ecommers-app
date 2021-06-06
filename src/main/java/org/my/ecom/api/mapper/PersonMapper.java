package org.my.ecom.api.mapper;

import org.my.ecom.api.modal.Person;
import org.my.ecom.entities.Persons;

import java.util.List;

//@Mapper
public interface PersonMapper {
    Person entityToUi(Persons persons, boolean isCategory);
    Persons uiToEntity(Person person, boolean isCategory);

    List<Person> entityToUiList(List<Persons> persons, boolean isCategory);
    List<Persons> uiToEntityList(List<Person> person, boolean isCategory);
}
