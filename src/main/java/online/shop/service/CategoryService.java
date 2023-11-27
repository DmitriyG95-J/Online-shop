package online.shop.service;

import online.shop.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    /**
     * <p>Возвращает список всех категорий товаров</p>
     *
     * @return Список категорий
     */
    List<CategoryDTO> findAll();
}
