package com.vivo.territory.Service.Auth;

import com.vivo.territory.Domain.Email.EmailMessage;
import com.vivo.territory.Domain.OtpVerification.Entity.OtpVerification;
import com.vivo.territory.Domain.OtpVerification.Repository.OtpVerificationRepository;
import com.vivo.territory.Domain.User.DTO.DTOUserLogin;
import com.vivo.territory.Domain.User.Entity.User;
import com.vivo.territory.Domain.User.Enums.StatusEnum;
import com.vivo.territory.Domain.User.Repository.UserRespository;
import com.vivo.territory.Domain.UserVerification.DTO.DTOUserVerificationStatusRequest;
import com.vivo.territory.Domain.UserVerification.Entity.UserVerification;
import com.vivo.territory.Domain.UserVerification.Repository.UserVerificationRepository;
import com.vivo.territory.Infra.Exception.Errortreatment;
import com.vivo.territory.Infra.Security.DTOTokenJWT;
import com.vivo.territory.Infra.Security.TokenService;
import com.vivo.territory.Service.Email.EmailPublisher;
import com.vivo.territory.Service.html.HtmlTemplateService;
import com.vivo.territory.Utils.EncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private HtmlTemplateService htmlTemplateService;
    @Autowired
    private EmailPublisher emailPublisher;

    @Autowired
    private UserRespository userRespository;
    @Autowired
    private OtpVerificationRepository otpVerificationRepository;


    public DTOTokenJWT Login(DTOUserLogin dtoUsuarioLogin) {
        try {

            var authenticationToken = new UsernamePasswordAuthenticationToken(dtoUsuarioLogin.login(), dtoUsuarioLogin.senha());
            var authentication = manager.authenticate(authenticationToken);
            User user = (User) authentication.getPrincipal();

            // Verifica se o status do usuário é PENDENTE
            if (user.getStatus() == StatusEnum.P) {
                throw new Errortreatment.UsuarioPendenteException("Usuário pendente");
            }

            OtpVerification del = otpVerificationRepository.findByUser(user);
            if(del != null){
            otpVerificationRepository.delete(del);
            }

            // Geração do código OTP para 2FA
            String otpCode = generateOtpCode();
            OtpVerification otpVerification = new OtpVerification(user);


            // Envio do código por e-mail
            String mensagemHtml = htmlTemplateService.getTemplateContent(2L); // Template específico para 2FA
            EmailMessage emailMessage = getEmailMessage(user, mensagemHtml, otpCode);
            emailPublisher.publishEmail(emailMessage);

            // Salva o OTP gerado
            otpVerification.setVerificationCode(EncryptDecrypt.encrypt(otpCode, EncryptDecrypt.getSecretKey()));
            otpVerificationRepository.save(otpVerification);

            // Retorna uma mensagem pedindo o código OTP
            return new DTOTokenJWT("Código de verificação enviado. Verifique seu e-mail para continuar.");

        } catch (Errortreatment.UsuarioPendenteException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Login ou senha inválidos");
        }
    }

    public DTOTokenJWT verifyOtp(DTOUserVerificationStatusRequest dtoUser) {
        try {
            // Busca o usuário e sua verificação pendente
            User user = userRespository.getReferenceByCpf(dtoUser.cpf());
            OtpVerification otpVerification = otpVerificationRepository.findByUser(user);

            // Descriptografa o código salvo e compara com o código fornecido pelo usuário
            String decryptedOtpCode = EncryptDecrypt.decrypt(otpVerification.getVerificationCode(), EncryptDecrypt.getSecretKey());

            if (!decryptedOtpCode.equals(dtoUser.verificationCode())) {
                throw new RuntimeException("Código de verificação inválido.");
            }

            // Se o código OTP for válido, gera o token JWT e completa o login
            var tokenJWT = tokenService.generateToken(user);
            return new DTOTokenJWT(tokenJWT);

        } catch (Exception e) {
            throw new RuntimeException("Erro na verificação do código.");
        }
    }

    private String generateOtpCode() {
        int otp = (int)(Math.random() * 900000) + 100000; // Gera um código de 6 dígitos
        return String.valueOf(otp);
    }

    private static EmailMessage getEmailMessage(User user, String mensagemHtml, String opt) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(user.getEmail());
        emailMessage.setSubject("Codigo de acesso");
        emailMessage.setBody(mensagemHtml.replace("1 2 3 4 5 6", opt));
        return emailMessage;
    }
}
