package com.dbproject.makeup.service;

import com.dbproject.makeup.po.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    Product getProduct(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    List<Product> listProduct();

    Page<Product> listProduct(Pageable pageable);

    Page<Product> listProduct(Pageable pageable, Long detailedCategoryId);

    List<Product> listProduct(String productIds);
}
