package online.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import online.shop.model.Category;
import online.shop.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    @Min(1)
    @Max(1_000_000_000)
    private BigDecimal price;

    @NotNull
    private Category category;

    public static ProductDTO valueOf(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getCategory()
        );
    }

    public Product mapToProduct() {
        return new Product(id, title, price, category);
    }
}