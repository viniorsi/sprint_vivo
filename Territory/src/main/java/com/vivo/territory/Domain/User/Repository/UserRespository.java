package com.vivo.territory.Domain.User.Repository;

import com.vivo.territory.Domain.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRespository extends JpaRepository<User, Long> {

    User getReferenceByCpf(String cpf);
}
