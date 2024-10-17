package com.vivo.territory.Service.Products;

import com.vivo.territory.Domain.Products.DTO.ProductsDTO;
import com.vivo.territory.Domain.Products.Entity.Product;
import com.vivo.territory.Domain.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    @Autowired
    ProductRepository productRepository;

    public Page<ProductsDTO> listProducts(Pageable pagination) throws Exception {
        try{
            Page<Product> products = productRepository.findAll(pagination);
            return products.map(ProductsDTO::new);
        }catch (Exception e) {
            throw new Exception("problema na listagem");
        }

    }


}
