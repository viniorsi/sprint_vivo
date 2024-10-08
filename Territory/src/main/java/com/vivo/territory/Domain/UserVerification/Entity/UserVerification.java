package com.vivo.territory.Domain.UserVerification.Entity;


import com.vivo.territory.Domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Random;

@Table(name = "usersverification")
@Entity(name = "UserVerification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String verificationCode;

    private LocalDateTime expirationDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

     public UserVerification(User user){
         Random random = new Random();

        this.user = user;
        this.verificationCode = String.valueOf(100000 + random.nextInt(900000));
        this.expirationDate = LocalDateTime.now();
    }
}
