package com.dbproject.makeup.service;

import com.dbproject.makeup.po.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    Product saveProduct(Product product);

    Product getProduct(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Page<Product> listProduct(Pageable pageable);

    Page<Product> listProduct(Pageable pageable, Long detailedCategoryId);
}
