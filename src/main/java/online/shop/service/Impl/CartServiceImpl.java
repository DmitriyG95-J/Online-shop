package online.shop.service.Impl;

import lombok.extern.log4j.Log4j2;
import online.shop.component.Cart;
import online.shop.dto.CartDTO;
import online.shop.dto.CartItemDTO;
import online.shop.dto.ProductDTO;
import online.shop.model.Product;
import online.shop.service.CartService;
import online.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CartServiceImpl implements CartService {

    private final Cart cart;
    private final ProductService productService;

    @Autowired
    public CartServiceImpl(Cart cart, ProductService productService) {
        this.cart = cart;
        this.productService = productService;
    }

    @Override
    public CartDTO find() {
        Map<Long, Integer> products = cart.getProducts();

        List<CartItemDTO> items = products.keySet()
                .stream()
                .map(key -> {
                    ProductDTO productDto = productService.findById(key);
                    CartItemDTO cartItemDto = new CartItemDTO();
                    cartItemDto.setProductTitle(productDto.getTitle());
                    cartItemDto.setCategoryName(productDto.getCategory().getName());
                    cartItemDto.setPricePerProduct(productDto.getPrice());
                    cartItemDto.setQuantity(products.get(key));
                    cartItemDto.setValue(BigDecimal.valueOf(products.get(key)).multiply(productDto.getPrice()));
                    return cartItemDto;
                })
                .collect(Collectors.toList());

        CartDTO result = new CartDTO();
        result.setItems(items);
        result.setTotalCost(
                items.stream()
                        .map(it -> it.getValue())
                        .reduce((o1, o2) -> o1.add(o2))
                        .orElse(BigDecimal.ZERO)
        );

        log.debug("Вернуть корзину со всеми товарами: " + result);
        return result;
    }

    @Override
    public void add(Long productId) {
        Product product = productService.findById(productId).mapToProduct();
        log.debug("Добавить в корзину товар: " + product);
        cart.add(productId);
    }

    @Override
    public void delete() {
        log.debug("Очистить корзину");
        cart.delete();
    }
}