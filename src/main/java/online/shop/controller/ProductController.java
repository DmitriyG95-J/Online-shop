package online.shop.controller;


import online.shop.dto.ProductDTO;
import online.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
//Контроллер для товаров
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/getProductPage")
    @ResponseStatus(HttpStatus.OK)
    //Получить страницу с товарами
    public Page<ProductDTO> findProductPage(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "pageIndex", required = false) Integer pageIndex
    ) {
        return productService.findProductPage(params, pageIndex);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("permitAll()")
    //Получить товар по id
    public ProductDTO findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    //Добавить новый товар в каталог
    public ProductDTO save(@RequestBody @Valid ProductDTO productDto) {
        return productService.save(productDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    //Обновить товар
    public void update(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO productDto) {
        productService.update(id, productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //Удалить товар
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}
