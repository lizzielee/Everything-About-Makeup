package com.dbproject.makeup.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    private String info;

    // Relationship: Review - Product
    @ManyToMany(mappedBy = "relatedProductList")
    private List<Review> reviewList = new ArrayList<>();

    // Relationship: Product - DetailedProductCategory
    @ManyToOne
    private DetailedProductCategory detailedProductCategory;

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public DetailedProductCategory getDetailedProductCategory() {
        return detailedProductCategory;
    }

    public void setDetailedProductCategory(DetailedProductCategory detailedProductCategory) {
        this.detailedProductCategory = detailedProductCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", info='" + info + '\'' +
                '}';
    }
}
