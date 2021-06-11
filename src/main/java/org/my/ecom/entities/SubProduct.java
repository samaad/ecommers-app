package org.my.ecom.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subproduct")
public class SubProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spid;
    private String subproducName;
    private String description;
    private String productImg;

    @OneToOne
    @JoinColumn(name = "parentSubProduct_id")
    public SubProduct parentSubProduct;

    @OneToMany(mappedBy="parentSubProduct")
    public Set<SubProduct> childSubProduct;

    private Long pid;

    public SubProduct() {
    }

    public SubProduct(Long spid, String subproducName, String description, String productImg){
        this.spid = spid;
        this.subproducName = subproducName;
        this.description = description;
        this.productImg = productImg;
    }

    public Long getSpid() {
        return spid;
    }

    public void setSpid(Long spid) {
        this.spid = spid;
    }

    public String getSubproducName() {
        return subproducName;
    }

    public void setSubproducName(String subproducName) {
        this.subproducName = subproducName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public SubProduct getParentSubProduct() {
        return parentSubProduct;
    }

    public void setParentSubProduct(SubProduct parentSubProduct) {
        this.parentSubProduct = parentSubProduct;
    }

    public Set<SubProduct> getChildSubProduct() {
        return childSubProduct;
    }

    public void setChildSubProduct(Set<SubProduct> childSubProduct) {
        this.childSubProduct = childSubProduct;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
