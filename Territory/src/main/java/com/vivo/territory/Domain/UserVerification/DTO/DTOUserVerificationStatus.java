package com.vivo.territory.Domain.UserVerification.DTO;


import com.vivo.territory.Domain.User.Entity.User;

public record DTOUserVerificationStatus(
        String cpf,
        String name,
        String email,
        String status

) {
    public DTOUserVerificationStatus(User user) {
        this(
                user.getCpf(),
                user.getName(),
                user.getEmail(),
                user.getStatus().getDescricao());
    }


}
