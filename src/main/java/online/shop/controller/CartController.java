package online.shop.controller;


import online.shop.dto.CartDTO;
import online.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
//Контроллер для корзины
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    //Получить корзину со всеми товарами
    public CartDTO find() {
        return cartService.find();
    }

    @PutMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    //Добавить товар в корзину
    public void add(@RequestParam("productId") Long productId) {
        cartService.add(productId);
    }

    @DeleteMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //Очистить корзину
    public void delete() {
        cartService.delete();
    }
}
