package online.shop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {

    private String productTitle;
    private String categoryName;
    private BigDecimal pricePerProduct;
    private Integer quantity;
    private BigDecimal value;
}
