package online.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.shop.dto.CategoryDTO;
import online.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Api(description = "Контроллер для категорий товаров")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить все категории товаров")
    public List<CategoryDTO> findAll() {
        return categoryService.findAll();
    }
}
