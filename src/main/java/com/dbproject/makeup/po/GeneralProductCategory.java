package com.dbproject.makeup.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product_cat_g")
public class GeneralProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long generalCategoryId;

    private String generalCategoryName;

    // Relationship: DetailedProductCategory - GeneralProductCategory
    @OneToMany(mappedBy = "generalProductCategory")
    private List<DetailedProductCategory> detailedProductCategoryList = new ArrayList<>();

    public GeneralProductCategory() {
    }

    public Long getGeneralCategoryId() {
        return generalCategoryId;
    }

    public void setGeneralCategoryId(Long generalCategoryId) {
        this.generalCategoryId = generalCategoryId;
    }

    public String getGeneralCategoryName() {
        return generalCategoryName;
    }

    public void setGeneralCategoryName(String generalCategoryName) {
        this.generalCategoryName = generalCategoryName;
    }

    public List<DetailedProductCategory> getDetailedProductCategoryList() {
        return detailedProductCategoryList;
    }

    public void setDetailedProductCategoryList(List<DetailedProductCategory> detailedProductCategoryList) {
        this.detailedProductCategoryList = detailedProductCategoryList;
    }

    @Override
    public String toString() {
        return "GeneralProductCategory{" +
                "generalCategoryId=" + generalCategoryId +
                ", generalCategoryName='" + generalCategoryName + '\'' +
                ", detailedProductCategoryList=" + detailedProductCategoryList +
                '}';
    }
}
