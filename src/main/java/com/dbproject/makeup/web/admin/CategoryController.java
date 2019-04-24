package com.dbproject.makeup.web.admin;

import com.dbproject.makeup.po.DetailedProductCategory;
import com.dbproject.makeup.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String list() {
        return "redirect:/admin/categories/1";
    }

    @GetMapping("/categories/{g_cat}")
    public String list(@Qualifier("detailed") @PageableDefault(size = 50, sort = {"detailedCategoryId"}, direction = Sort.Direction.ASC)
                                   Pageable dPageable,
                       @Qualifier("general") @PageableDefault(size = 50, sort = {"generalCategoryId"}, direction = Sort.Direction.ASC)
                               Pageable gPageable,
                       @PathVariable Long g_cat,
                       Model model) {

        model.addAttribute("g_categories", categoryService.listGeneralProductCategory(gPageable));
        model.addAttribute("d_categories", categoryService.listDetailedProductCategory(dPageable, g_cat));
        model.addAttribute("activeGeneralCategory", g_cat);

        // For editing/new detailed category.
        model.addAttribute("detailedProductCategory", new DetailedProductCategory());

        return "admin/categories";
    }

    @PostMapping("/categories/{g_cat}")
    public String post(DetailedProductCategory detailedProductCategory,
                       @PathVariable Long g_cat,
                       RedirectAttributes attributes,
                       HttpSession session) {
        detailedProductCategory.setGeneralProductCategory(categoryService.getGeneralProductCategory(g_cat));
        DetailedProductCategory afterOpCategory = null;
        if(detailedProductCategory.getDetailedCategoryName() == null || detailedProductCategory.getDetailedCategoryName().length() == 0) {     // Delete
            categoryService.deleteDetailedProductCategory(detailedProductCategory.getDetailedCategoryId());
        }
        else if(detailedProductCategory.getDetailedCategoryId() == null) {   // New
            afterOpCategory = categoryService.saveDetailedProductCategory(detailedProductCategory);
        }
        else {  // Edit
            afterOpCategory = categoryService.updateDetailedProductCategory(detailedProductCategory.getDetailedCategoryId(), detailedProductCategory);
        }
        if(afterOpCategory == null) {
            attributes.addFlashAttribute("message", "Failed to add detailed category" + detailedProductCategory.getDetailedCategoryName());
        }

        return "redirect:/admin/categories/" + g_cat;
    }
}
