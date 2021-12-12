package com.api.service;

import com.api.repository.ProductRepository;
import com.api.repository.entity.Product;
import com.api.repository.entity.VendorEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product addProduct(String productName, VendorEnum vendorEnum, String productDescription,
                              int productQuantity, Double productPrice) {
        Product product = Product.builder()
                .productName(productName)
                .vendorEnum(vendorEnum)
                .productDescription(productDescription)
                .productQuanity(productQuantity)
                .productPrice(productPrice)
                .build();
        return productRepository.save(product);
    }

    public boolean updateProduct(long id, Product updatedProduct) {
        Optional<Product> currentProduct = productRepository.findById(id);
        if (currentProduct.isPresent()) {
            Product newProduct = currentProduct.get();
            if (updatedProduct.getProductName() != null) {
                newProduct.setProductName(updatedProduct.getProductName());
            }
            if (updatedProduct.getVendorEnum() != null) {
                newProduct.setVendorEnum(updatedProduct.getVendorEnum());
            }
            if (updatedProduct.getProductDescription() != null) {
                newProduct.setProductDescription(updatedProduct.getProductDescription());
            }
            if (updatedProduct.getProductQuanity() > 0) {
                newProduct.setProductQuanity(updatedProduct.getProductQuanity());
            }
            if (updatedProduct.getProductPrice() > 0) {
                newProduct.setProductPrice(updatedProduct.getProductPrice());
            }
            newProduct.setId(id);
            productRepository.save(newProduct);
            return true;
        }
        return false;
    }
}
