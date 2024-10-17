package com.vivo.territory.Controller;

import com.vivo.territory.Domain.Products.DTO.ProductsDTO;
import com.vivo.territory.Service.Products.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productService;

    @GetMapping
    public ResponseEntity<Page<ProductsDTO>> list(@PageableDefault(size = 10, page = 0,sort = {"id"}) Pageable paginacao) throws Exception {

        var page = productService.listProducts(paginacao);
        return ResponseEntity.ok(page);

    }


}
