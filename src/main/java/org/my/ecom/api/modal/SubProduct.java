package org.my.ecom.api.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubProduct {

    private Long spid;
    private String subproducName;
    private String productImg;
}
