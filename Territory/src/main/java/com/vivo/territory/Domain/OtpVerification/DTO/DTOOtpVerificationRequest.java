package com.vivo.territory.Domain.OtpVerification.DTO;

public record DTOOtpVerificationRequest(
        String cpf,
        String verificationCode
) {
}
