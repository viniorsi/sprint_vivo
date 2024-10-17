package com.vivo.territory.Domain.UserProduct.DTO;

import com.vivo.territory.Domain.Products.Entity.Product;
import com.vivo.territory.Domain.UserProduct.Entity.UserProduct;

import java.time.LocalDateTime;

public record DTOUserProductDetails(
        Long id,
        String product_name,
        String description,
        boolean status,
        LocalDateTime dt_contratacao
) {

    public DTOUserProductDetails(UserProduct userProduct) {

        this(userProduct.getProduct().getId(),
                userProduct.getProduct().getName(),
                userProduct.getProduct().getDescription(),
                userProduct.isStatus(),
                userProduct.getData_contratacao());


    }


}
