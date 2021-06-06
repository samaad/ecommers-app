package org.my.ecom.api.mapper;

import org.my.ecom.api.modal.Product;


import java.util.List;
import java.util.Set;

public interface ProductMapper {
    Product entityToUi(org.my.ecom.entities.Product persons);
    org.my.ecom.entities.Product uiToEntity(Product person);

    Set<Product> entityToUiList(Set<org.my.ecom.entities.Product> persons);
    Set<org.my.ecom.entities.Product> uiToEntityList(Set<Product> person);
}
