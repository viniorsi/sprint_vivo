package com.vivo.territory.Domain.User.DTO;



import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DTOUserRegister(

        @NotBlank @CPF
        String cpf,
        @NotBlank
        String name,
        @NotBlank
        String tel,
        @NotBlank
        String ddd,
        @NotBlank @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        LocalDate birthday


) {
}
