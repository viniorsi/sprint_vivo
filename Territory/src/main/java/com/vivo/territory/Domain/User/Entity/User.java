package com.vivo.territory.Domain.User.Entity;

import com.vivo.territory.Domain.User.DTO.DTOUserRegister;
import com.vivo.territory.Domain.User.Enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id  ;
    private String cpf ;
    private String name ;
    private String tel ;
    private String ddd ;
    private String email ;
    private String password ;
    private LocalDate birthday ;
    @Enumerated(EnumType.STRING)
    private StatusEnum status ;

    public User(DTOUserRegister data) {
        this.cpf = data.cpf();
        this.name = data.name();
        this.tel = data.tel();
        this.ddd = data.ddd();
        this.email = data.email();
        this.password = data.password();
        this.birthday = data.birthday();
        this.status = StatusEnum.P;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
