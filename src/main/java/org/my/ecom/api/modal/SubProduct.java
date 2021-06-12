package org.my.ecom.api.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubProduct {

    private Long spid;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productImg;
}
