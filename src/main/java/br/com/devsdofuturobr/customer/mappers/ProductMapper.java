package br.com.devsdofuturobr.customer.mappers;

import br.com.devsdofuturobr.customer.dto.response.ProductCompleteResponse;
import br.com.devsdofuturobr.customer.dto.response.ProductShortResponse;
import br.com.devsdofuturobr.customer.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

public class ProductMapper {
    public static ProductShortResponse toShortDTO(Product product) {
        return new ProductShortResponse(
                product.getId(),
                product.getName()
        );
    }

    public static ProductCompleteResponse toCompleteDTO(Product product) {
        return new ProductCompleteResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription()
        );
    }

    public static PagedModel<ProductCompleteResponse> toPagedModel(Page<Product> products){
        return new PagedModel<>(products.map(ProductMapper::toCompleteDTO));
    }

    public static Page<ProductCompleteResponse> toPage(Page<Product> all) {
        return all.map(ProductMapper::toCompleteDTO);
    }
}
