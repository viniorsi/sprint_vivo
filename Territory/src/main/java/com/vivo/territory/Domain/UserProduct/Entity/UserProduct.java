package com.vivo.territory.Domain.UserProduct.Entity;


import com.vivo.territory.Domain.Products.Entity.Product;
import com.vivo.territory.Domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "user_product")
@Entity(name = "User_Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;
    @OneToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;
    private LocalDateTime data_contratacao;
    private boolean status;

    public UserProduct(User u, Product p) {

        this.user = u;
        this.product = p;
        this.data_contratacao = LocalDateTime.now();
        this.status = true;




    }
}
