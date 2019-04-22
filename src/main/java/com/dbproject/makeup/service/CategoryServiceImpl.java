package com.dbproject.makeup.service;

import com.dbproject.makeup.NotFoundException;
import com.dbproject.makeup.dao.DetailedCategoryRepository;
import com.dbproject.makeup.dao.GeneralCategoryRepository;
import com.dbproject.makeup.po.DetailedProductCategory;
import com.dbproject.makeup.po.GeneralProductCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final DetailedCategoryRepository detailedCategoryRepository;
    private final GeneralCategoryRepository generalCategoryRepository;

    @Autowired
    public CategoryServiceImpl(DetailedCategoryRepository detailedCategoryRepository, GeneralCategoryRepository generalCategoryRepository) {
        this.detailedCategoryRepository = detailedCategoryRepository;
        this.generalCategoryRepository = generalCategoryRepository;
    }

    @Transactional
    @Override
    public DetailedProductCategory saveDetailedProductCategory(DetailedProductCategory category) {
        return detailedCategoryRepository.save(category);
    }



    @Transactional
    @Override
    public DetailedProductCategory getDetailedProductCategory(Long id) {
        return detailedCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public Page<DetailedProductCategory> listDetailedProductCategory(Pageable pageable, Long generalCategoryId) {
        return detailedCategoryRepository.findAll(
                (Specification<DetailedProductCategory>) (root, criteriaQuery, criteriaBuilder)
                        -> criteriaBuilder.equal(root.<GeneralProductCategory>get("generalProductCategory")
                .get("generalCategoryId"), generalCategoryId), pageable);
    }

    @Transactional
    @Override
    public DetailedProductCategory updateDetailedProductCategory(Long id, DetailedProductCategory category) {
        DetailedProductCategory originCategory = detailedCategoryRepository.findById(id).orElse(null);
        if(originCategory == null) {
            throw new NotFoundException("Invalid category");
        }
        BeanUtils.copyProperties(category, originCategory);
        return detailedCategoryRepository.save(originCategory);
    }

    @Transactional
    @Override
    public void deleteDetailedProductCategory(Long id) {
        detailedCategoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public GeneralProductCategory saveGeneralProductCategory(GeneralProductCategory category) {
        return generalCategoryRepository.save(category);
    }

    @Transactional
    @Override
    public GeneralProductCategory getGeneralProductCategory(Long id) {
        return generalCategoryRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Page<GeneralProductCategory> listGeneralProductCategory(Pageable pageable) {
        return generalCategoryRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public GeneralProductCategory updateGeneralProductCategory(Long id, GeneralProductCategory category) {
        GeneralProductCategory originCategory = generalCategoryRepository.findById(id).orElse(null);
        if(originCategory == null) {
            throw new NotFoundException("Invalid category");
        }
        BeanUtils.copyProperties(category, originCategory);
        return generalCategoryRepository.save(originCategory);
    }

    @Transactional
    @Override
    public void deleteGeneralProductCategory(Long id) {
        generalCategoryRepository.deleteById(id);
    }
}
