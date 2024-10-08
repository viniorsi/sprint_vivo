package com.vivo.territory.Domain.User.DTO;

import com.vivo.territory.Domain.User.Entity.User;
import com.vivo.territory.Domain.UserVerification.Entity.UserVerification;

import java.time.LocalDate;

public record DTOUserDetails(

        String name,
        String email,
        String cpf,
        String ddd,
        String tel,
        LocalDate birthday,
        String status,
        String verificationCode

) {
    public DTOUserDetails(User user, UserVerification userVerification) {
        this(
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getDdd(),
                user.getTel(),
                user.getBirthday(),
                user.getStatus().getDescricao(),
                userVerification.getVerificationCode());
    }



}
