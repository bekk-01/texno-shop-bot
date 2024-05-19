package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;

public class ProductService extends BaseService<Product, ProductRepository> {
    private static final ProductService productService = new ProductService();
    private ProductService() {
        super(new ProductRepository());
    }

    public static ProductService getInstance() {
        return productService;
    }
}
