package hnt.spring.reactive.mongo.service;


import hnt.spring.reactive.mongo.dto.ProductDto;
import hnt.spring.reactive.mongo.repository.ProductRepository;
import hnt.spring.reactive.mongo.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Flux<ProductDto> getProducts() {
        return productRepository.findAll()
                .map(Utils::entityToDto);
    }

    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id)
                .map(Utils::entityToDto);
    }

    public Flux<ProductDto> getProductsInRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDto> createProduct(Mono<ProductDto> productDtoMono) {
       return productDtoMono.map(Utils::dtoToEntity)
               .flatMap(productRepository::save)
               .map(Utils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(Utils::dtoToEntity))
                .doOnNext(e -> e.setId(id))
                .flatMap(productRepository::save)
                .map(Utils::entityToDto);
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }
}
