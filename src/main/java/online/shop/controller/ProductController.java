package online.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.shop.dto.ProductDTO;
import online.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
@Api(description = "Контроллер для товаров")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // public interface MultiValueMap<K, V> extends Map<K, List<V>>
    // Extension of the Map interface that stores multiple values.
    //
    // Один ключ в Map соответствует одному значению.
    // Здесь используем MultiValueMap - по одному ключу получаем несколько значений.
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить страницу с товарами")
    public Page<ProductDTO> findProductPage(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam("pageIndex") Integer pageIndex
    ) {
        return productService.findProductPage(params, pageIndex);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить товар по id")
    public ProductDTO findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить новый товар в каталог")
    public ProductDTO save(@RequestBody @Valid ProductDTO productDto) {
        return productService.save(productDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Обновить товар")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO productDto) {
        productService.update(id, productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Удалить товар")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}
