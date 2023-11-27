package online.shop.service.Impl;

import lombok.extern.log4j.Log4j2;
import online.shop.dto.CategoryDTO;
import online.shop.repository.CategoryRepository;
import online.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> list = categoryRepository.findAll()
                .stream()
                .map(it -> CategoryDTO.valueOf(it))
                .collect(Collectors.toList());

        log.debug("Список всех категорий: " + list);
        return list;
    }
}