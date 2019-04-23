package com.dbproject.makeup.dao;

import com.dbproject.makeup.po.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
