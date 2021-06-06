package org.my.ecom.repository;

import org.my.ecom.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Query(value = "SELECT cat.cid as cid, cat.category_name as category_name, pcat.person_id\n" +
            "FROM categories AS cat\n" +
            "\tLEFT JOIN person_categories AS pcat\n" +
            "\tON pcat.cid = cat.cid\n" +
            "where pcat.person_id = :personId", nativeQuery = true)
    List<Categories> getCategoriesByPersonId(@Param("personId") Long personId);
}
