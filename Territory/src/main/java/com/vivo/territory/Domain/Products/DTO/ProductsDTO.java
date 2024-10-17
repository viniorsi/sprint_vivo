package com.vivo.territory.Domain.Products.DTO;

import com.vivo.territory.Domain.Products.Entity.Product;

public record ProductsDTO(
        Long id,
        String name,
        String description,
        Double price
) {

    public ProductsDTO(Product product){
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

}
