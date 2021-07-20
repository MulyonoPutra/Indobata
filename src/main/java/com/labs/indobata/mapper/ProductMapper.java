package com.labs.indobata.mapper;

import com.labs.indobata.domain.dto.ProductDTO;
import com.labs.indobata.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends EntityMapper<ProductDTO, Product>{
    @Mapping(target = "category_post", source = "category_product", qualifiedByName = "id")
    ProductDTO toDto(Product s);
}
