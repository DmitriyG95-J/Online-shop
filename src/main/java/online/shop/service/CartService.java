package online.shop.service;

import online.shop.dto.CartDTO;

public interface CartService {

    CartDTO find();

    void add(Long productId);

    void delete();
}
