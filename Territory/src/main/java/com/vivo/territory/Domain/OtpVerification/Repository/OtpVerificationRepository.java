package com.vivo.territory.Domain.OtpVerification.Repository;

import com.vivo.territory.Domain.OtpVerification.Entity.OtpVerification;
import com.vivo.territory.Domain.User.Entity.User;
import com.vivo.territory.Domain.UserVerification.Entity.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long>{

    OtpVerification findByUser(User user);
}
