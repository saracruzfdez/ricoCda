package com.cda.rico.controllers.enums;

import com.cda.rico.enums.CategoryEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @GetMapping
    public CategoryEnum[] getCategories() {
        return CategoryEnum.values();
    }
}
