package com.dbproject.makeup.service;

import com.dbproject.makeup.NotFoundException;
import com.dbproject.makeup.dao.ProductRepository;
import com.dbproject.makeup.po.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Product updateProduct(Long id, Product product) {
        Product originProduct = productRepository.findById(id).orElse(null);
        if(originProduct == null) {
            throw new NotFoundException("Invalid product");
        }
        BeanUtils.copyProperties(product, originProduct);
        return productRepository.save(originProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<Product> listProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Page<Product> listProduct(Pageable pageable, Long detailedCategoryId) {
        return productRepository.findAll(
                (Specification<Product>) (root, cq, cb) -> cb.equal(root.get("detailedProductCategory")
                        .get("detailedCategoryId"), detailedCategoryId), pageable);
    }
}
