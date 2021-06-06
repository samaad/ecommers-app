package org.my.ecom.repository;

import org.my.ecom.api.modal.Person;
import org.my.ecom.entities.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Persons, Long> {

    @Query(value = "SELECT per.person_id, \n" +
            "    per.gender, \n" +
            "    cat.cid,\n" +
            "\tcat.category_name,\n" +
            "\tpro.product_name \n" +
            "FROM persons AS per\n" +
            "    LEFT JOIN person_categories AS pcat\n" +
            "        ON per.person_id = pcat.person_id\n" +
            "    LEFT JOIN categories AS cat\n" +
            "        ON pcat.cid = cat.cid\n" +
            "\tLEFT JOIN product AS pro \n" +
            "\t\tON pcat.cid = pro.category_id", nativeQuery = true)
    List<Person> getAllProductList();
}
