package campaign.dto;

import campaign.repositories.model.ProductEntity;

import java.util.UUID;

public class ProductRequest {
    String title;
    String category;
    Integer price;
    String sellerId;

    public ProductEntity toProductEntity() {
        return ProductEntity.builder()
                .title(title)
                .category(category)
                .price(price)
                .sellerId(sellerId)
                .productId(UUID.randomUUID().toString())
                .build();
    }
}
