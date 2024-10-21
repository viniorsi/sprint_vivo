package com.vivo.territory.Domain.User.DTO;

import com.vivo.territory.Domain.User.Entity.User;

import java.time.LocalDate;

public record DTOUserCompleteDetails(
        Long id,
        String name,
        String email,
        String cpf,
        String ddd,
        String tel,
        LocalDate birthday,
        String status
) {

    public DTOUserCompleteDetails(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getDdd(),
                user.getTel(),
                user.getBirthday(),
                user.getStatus().getDescricao()
        );
    }


}
