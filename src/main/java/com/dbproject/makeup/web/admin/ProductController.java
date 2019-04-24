package com.dbproject.makeup.web.admin;

import com.dbproject.makeup.po.Product;
import com.dbproject.makeup.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String listProduct(Pageable pageable, Model model) {
        model.addAttribute("products", productService.listProduct(pageable));

        // For updating new product.
        model.addAttribute("product", new Product());

        return "admin/products";
    }

    @PostMapping("")
    public String post(Product product, RedirectAttributes attributes, HttpSession session) {
        if(product.getName() == null || product.getName().length() == 0) {  // Delete
            productService.deleteProduct(product.getProductId());
        }
        else if(product.getProductId() == null) {   // New Product
            productService.saveProduct(product);
        }
        else {  // Edit Product
            productService.updateProduct(product.getProductId(), product);
        }

        return "redirect:/admin/products";
    }

}
