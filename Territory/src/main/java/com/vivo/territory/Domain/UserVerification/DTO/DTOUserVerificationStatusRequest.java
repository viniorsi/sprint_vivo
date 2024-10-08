package com.vivo.territory.Domain.UserVerification.DTO;

public record DTOUserVerificationStatusRequest(
        String cpf,
        String verificationCode
) {
}
