package org.my.ecom.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "subproduct")
public class SubProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spid;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productImg;

    @OneToOne
    @JoinColumn(name = "parentSubProduct_id")
    public SubProduct parentSubProduct;

    @OneToMany(mappedBy="parentSubProduct")
    public Set<SubProduct> childSubProduct;

    private Long pid;

    public SubProduct() {
    }

    public SubProduct(Long spid, String productName, String productDescription, String productImg, BigDecimal productPrice){
        this.spid = spid;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImg = productImg;
        this.productPrice = productPrice;
    }

    public Long getSpid() {
        return spid;
    }

    public void setSpid(Long spid) {
        this.spid = spid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
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
