package online.shop.service;

import online.shop.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

public interface ProductService {

    /**
     * <p>Возвращает страницу с товарами</p>
     *
     * @param params    Параметры для фильтрации товаров
     * @param pageIndex Номер страницы
     */
    Page<ProductDTO> findProductPage(MultiValueMap<String, String> params, Integer pageIndex);

    /**
     * <p>Возвращает товар по идентификатору</p>
     *
     * @param id Идентификатор
     * @return Товар
     */
    ProductDTO findById(Long id);

    /**
     * <p>Добавляет новый товар в каталог</p>
     *
     * @param productDto Данные для добавления товара
     * @return Сохранённый в БД товар
     */
    ProductDTO save(ProductDTO productDto);

    /**
     * <p>Обновляет товар</p>
     *
     * @param id         Идентификатор товара
     * @param productDto Данные для обновления товара
     */
    void update(Long id, ProductDTO productDto);

    /**
     * <p>Удаляет товар из БД</p>
     *
     * @param id Идентификатор товара
     */
    void delete(Long id);
}
