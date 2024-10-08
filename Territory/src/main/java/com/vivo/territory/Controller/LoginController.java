package com.vivo.territory.Controller;

import com.vivo.territory.Domain.User.DTO.DTOUserLogin;
import com.vivo.territory.Domain.UserVerification.DTO.DTOUserVerificationStatusRequest;
import com.vivo.territory.Service.Auth.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/singin")
    public ResponseEntity singIn(@RequestBody @Valid DTOUserLogin dtoUsuarioLogin) {
        try {
            var token = loginService.Login(dtoUsuarioLogin);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity verifyOtp(@RequestBody @Valid DTOUserVerificationStatusRequest dtoUsuario) {
        try {
            var token = loginService.verifyOtp(dtoUsuario);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
