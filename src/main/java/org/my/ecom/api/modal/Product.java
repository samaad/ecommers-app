package org.my.ecom.api.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.my.ecom.entities.SubProduct;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long pid;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Long cid;
    private Long personId;
    private String productImg;
    private Set<SubProduct> subProducts;
}
