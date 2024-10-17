package com.vivo.territory.Domain.Products.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id  ;
    private String name;
    private String description;
    private Double price;
}
