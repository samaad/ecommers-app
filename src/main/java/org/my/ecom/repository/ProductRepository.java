package org.my.ecom.repository;

import org.my.ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Set<Product> findByCidAndPersonId(Long cid, Long personId);
    List<Product> findByCid(Long cid);
}
