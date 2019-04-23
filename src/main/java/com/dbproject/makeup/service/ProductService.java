package com.dbproject.makeup.service;

import com.dbproject.makeup.po.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product saveProduct(Product product);

    Product getProduct(Long id);

    Page<Product> listProduct(Pageable pageable);
}
