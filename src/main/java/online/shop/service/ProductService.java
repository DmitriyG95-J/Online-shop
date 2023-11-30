package online.shop.service;

import online.shop.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

public interface ProductService {

    Page<ProductDTO> findProductPage(MultiValueMap<String, String> params, Integer pageIndex);

    ProductDTO findById(Long id);

    ProductDTO save(ProductDTO productDto);

    void update(Long id, ProductDTO productDto);

    void delete(Long id);
}
