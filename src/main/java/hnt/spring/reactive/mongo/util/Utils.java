package hnt.spring.reactive.mongo.util;

import hnt.spring.reactive.mongo.dto.ProductDto;
import hnt.spring.reactive.mongo.entity.Product;
import org.springframework.beans.BeanUtils;

public class Utils {

    public static ProductDto entityToDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public static Product dtoToEntity(ProductDto dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }
}
