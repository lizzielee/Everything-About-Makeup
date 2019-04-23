package com.dbproject.makeup.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String name;
    private String info;
    private String image;

    // Relationship: Review - Product
    @ManyToMany(mappedBy = "relatedProductList")
    private List<Review> reviewList = new ArrayList<>();

    // Relationship: Product - DetailedProductCategory
    @ManyToOne
    private DetailedProductCategory detailedProductCategory;

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", image='" + image + '\'' +
                ", detailedProductCategory=" + detailedProductCategory +
                '}';
    }
}
