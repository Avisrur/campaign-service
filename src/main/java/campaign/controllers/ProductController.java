package campaign.controllers;

import campaign.dto.ProductRequest;
import campaign.repositories.model.ProductEntity;
import campaign.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest) {
        log.info("Got request {}", productRequest);
        ProductEntity productEntity = productService.saveProduct(productRequest.toProductEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(productEntity);
    }

//    @GetMapping
//    public ResponseEntity<List<ProductEntity>> getProducts(@RequestParam Map<String, String> queryParams) {
//        log.info("About to retrieve products by: {}", queryParams);
//        return ResponseEntity.status(HttpStatus.OK).body(productService.findBy(queryParams));
//    }
//
//    @PutMapping("/{productId}")
//    public ResponseEntity<ProductEntity> updateProduct(@PathVariable String productId, @RequestBody ProductUpdate productUpdate) {
//        log.info("About to update product: {} with new body: {}", productId,productUpdate);
//        ProductEntity updatedProductEntity = productService.updateProduct(productId, productUpdate);
//        return ResponseEntity.status(HttpStatus.CREATED).body(updatedProductEntity);
//    }
//
//    @DeleteMapping("/{productId}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
//        productService.deleteById(productId);
//        log.info("Product with id {} was deleted", productId);
//        return ResponseEntity.status(204).build();
//    }


//    @ExceptionHandler({UnknownQueryParamException.class, ProductNotFoundException.class})
//    public void handleBadRequest(RuntimeException e, HttpServletResponse response) throws IOException {
//        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//    }
//
//    @ExceptionHandler(UpdateFailureException.class)
//    public void handleUnknownUpdateCriteria(RuntimeException e, HttpServletResponse response) throws IOException {
//        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
//    }
}
