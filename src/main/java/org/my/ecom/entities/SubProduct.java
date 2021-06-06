package org.my.ecom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subproduct")
public class SubProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spid;
    private String subproducName;
    private String description;
    private String productImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentSubProduct_id")
    public SubProduct parentSubProduct;

    @OneToMany(mappedBy="parentSubProduct")
    public Set<SubProduct> childSubProduct;

    private Long pid;
}
