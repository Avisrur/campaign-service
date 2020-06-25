package campaign.services;

import campaign.repositories.ProductRepository;
import campaign.repositories.model.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity saveProduct(ProductEntity newProduct) {
        return productRepository.save(newProduct);
    }
}
