package online.shop.service;

import online.shop.dto.CartDTO;

public interface CartService {

    /**
     * <p>Возвращает корзину со всеми товарами</p>
     *
     * @return Корзина
     */
    CartDTO find();

    /**
     * <p>Добавляет товар в корзину</p>
     *
     * @param productId Идентификатор товара
     */
    void add(Long productId);

    /**
     * <p>Очищает корзину</p>
     */
    void delete();
}
