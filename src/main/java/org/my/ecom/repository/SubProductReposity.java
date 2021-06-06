package org.my.ecom.repository;

import org.my.ecom.entities.SubProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubProductReposity extends JpaRepository<SubProduct, Long> {

    List<SubProduct> findByPid(Long id);
}
