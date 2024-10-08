package com.vivo.territory.Service.User;

import com.vivo.territory.Domain.Email.EmailMessage;
import com.vivo.territory.Domain.User.DTO.DTOUserDetails;
import com.vivo.territory.Domain.User.DTO.DTOUserRegister;
import com.vivo.territory.Domain.User.Entity.User;
import com.vivo.territory.Domain.User.Repository.UserRespository;
import com.vivo.territory.Domain.UserVerification.Entity.UserVerification;
import com.vivo.territory.Domain.UserVerification.Repository.UserVerificationRepository;
import com.vivo.territory.Service.Email.EmailPublisher;
import com.vivo.territory.Service.html.HtmlTemplateService;
import com.vivo.territory.Utils.EncryptDecrypt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserRegisterService {

    @Autowired
    private UserRespository userRepository;
    @Autowired
    private UserVerificationRepository userVerificationRepository;
    @Autowired
    private EncryptDecrypt encryptDecrypt;
    @Autowired
    private HtmlTemplateService htmlTemplateService;
    @Autowired
    private EmailPublisher emailPublisher;


    public DTOUserDetails createUser(@Valid DTOUserRegister userData) throws Exception {
        var user = new User(userData);
        var userExists = userRepository.getReferenceByCpf(user.getCpf());

        if (userExists == null) {
            user.setPassword(encryptDecrypt.passwordEncoder().encode(user.getPassword()));
            UserVerification userVerification = new UserVerification(user);
            var secret = EncryptDecrypt.getSecretKey();

                String mensagemHtml = htmlTemplateService.getTemplateContent(1L);

                EmailMessage emailMessage = getEmailMessage(user, mensagemHtml, userVerification);

                emailPublisher.publishEmail(emailMessage);

            userRepository.save(user);
            userVerification.setVerificationCode(EncryptDecrypt.encrypt(userVerification.getVerificationCode(), secret));
            userVerificationRepository.save(userVerification);
            return new DTOUserDetails(user, userVerification);
        } else {
            throw new Exception("Usuario ja existente");
        }
    }

    private static EmailMessage getEmailMessage(User user, String mensagemHtml, UserVerification userVerification) {
//        List<String> caminhosImagems = Arrays.asList(
//                "src/Assets/email/TravelEase.png",
//                "src/Assets/email/ImagemTravelEaseEmail.jpg"
//        );
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(user.getEmail());
        emailMessage.setSubject("Seja bem vindo!");
        emailMessage.setBody(mensagemHtml.replace("1 2 3 4 5 6", userVerification.getVerificationCode()));
//        emailMessage.setAttachments(caminhosImagems);
        return emailMessage;
    }
}
