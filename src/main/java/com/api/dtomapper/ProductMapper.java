package com.api.dtomapper;

import com.api.dto.ProductResponse;
import com.api.repository.ProductRepository;
import com.api.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse convertToProductDTO(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductName(product.getProductName());
        response.setVendorEnum(product.getVendorEnum());
        response.setProductDescription(product.getProductDescription());
        response.setProductQuantity(product.getProductQuanity());
        response.setProductPrice(product.getProductPrice());
        return response;
    }
}
