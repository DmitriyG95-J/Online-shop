package online.shop.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Не найден товар по id=" + id);
    }
}