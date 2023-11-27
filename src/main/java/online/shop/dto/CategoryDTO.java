package online.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.shop.model.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;

    public static CategoryDTO valueOf(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    public Category mapToCategory() {
        return new Category(id, name);
    }
}