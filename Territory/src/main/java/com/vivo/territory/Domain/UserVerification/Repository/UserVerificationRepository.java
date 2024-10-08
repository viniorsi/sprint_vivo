package com.vivo.territory.Domain.UserVerification.Repository;

import com.vivo.territory.Domain.User.Entity.User;
import com.vivo.territory.Domain.UserVerification.Entity.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long>{

    UserVerification findByUser(User user);
}
