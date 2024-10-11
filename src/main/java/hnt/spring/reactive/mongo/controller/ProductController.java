package hnt.spring.reactive.mongo.controller;

import hnt.spring.reactive.mongo.dto.ProductDto;
import hnt.spring.reactive.mongo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mongo/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }


    @GetMapping("/product-range")
    public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return productService.getProductsInRange(min, max);
    }

    @PostMapping()
    public Mono<ProductDto> createProduct(@RequestBody Mono<ProductDto> product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> product, @PathVariable String id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
            return productService.deleteProduct(id);
    }


}
