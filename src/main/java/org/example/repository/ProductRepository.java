package org.example.repository;

import org.example.model.Product;

public class ProductRepository extends BaseRepository<Product> {
    public ProductRepository() {
        super.path = "src/main/resources/product.json";
        super.type = Product.class;
    }
}
