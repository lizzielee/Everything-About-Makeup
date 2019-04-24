package com.dbproject.makeup.web.admin;

import com.dbproject.makeup.po.Product;
import com.dbproject.makeup.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProduct(Pageable pageable, Model model) {
        model.addAttribute("products", productService.listProduct(pageable));

        // For updating new product.
        model.addAttribute("product", new Product());

        return "admin/products";
    }
}
