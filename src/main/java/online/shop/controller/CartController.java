package online.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.shop.dto.CartDTO;
import online.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@Api(description = "Контроллер для корзины")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить корзину со всеми товарами")
    public CartDTO find() {
        return cartService.find();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Добавить товар в корзину")
    public void add(@RequestParam("productId") Long productId) {
        cartService.add(productId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Очистить корзину")
    public void delete() {
        cartService.delete();
    }
}
