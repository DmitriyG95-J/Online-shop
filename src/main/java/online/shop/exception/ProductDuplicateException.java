package online.shop.exception;

public class ProductDuplicateException extends RuntimeException {

    public ProductDuplicateException(String title) {
        super("В БД уже есть товар с названием: '" + title + "'");
    }
}