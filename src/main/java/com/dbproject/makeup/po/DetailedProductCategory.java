package com.dbproject.makeup.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product_cat_d")
public class DetailedProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int detailedCategoryId;

    private String detailedCategoryName;

    // Relationship: Product - DetailedProductCategory
    @OneToMany(mappedBy = "detailedProductCategory")
    private List<Product> productList = new ArrayList<>();

    // Relationship: DetailedProductCategory - GeneralProductCategory
    @ManyToOne
    private GeneralProductCategory generalProductCategory;

    public DetailedProductCategory() {
    }

    public int getDetailedCategoryId() {
        return detailedCategoryId;
    }

    public void setDetailedCategoryId(int detailedCategoryId) {
        this.detailedCategoryId = detailedCategoryId;
    }

    public String getDetailedCategoryName() {
        return detailedCategoryName;
    }

    public void setDetailedCategoryName(String detailedCategoryName) {
        this.detailedCategoryName = detailedCategoryName;
    }

    public GeneralProductCategory getGeneralProductCategory() {
        return generalProductCategory;
    }

    public void setGeneralProductCategory(GeneralProductCategory generalProductCategory) {
        this.generalProductCategory = generalProductCategory;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "DetailedProductCategory{" +
                "detailedCategoryId=" + detailedCategoryId +
                ", detailedCategoryName='" + detailedCategoryName + '\'' +
                ", generalProductCategory=" + generalProductCategory +
                '}';
    }
}
