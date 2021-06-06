package org.my.ecom.api.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categories {
    private Long cid;
    private String categoryName;
    private String categoryImg;
    Set<Product> products;
}
