package com.vivo.territory.Domain.UserProduct.Repository;

import com.vivo.territory.Domain.UserProduct.Entity.UserProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Long> {

@Query("Select up from User_Product up where up.user.id=:id_user and up.product.id=:id_product")
UserProduct findUserProduct(@Param("id_product") Long productId, @Param("id_user") Long userId);

    Page<UserProduct> findAllByUser_Id(Long userId, Pageable pageable);

}
