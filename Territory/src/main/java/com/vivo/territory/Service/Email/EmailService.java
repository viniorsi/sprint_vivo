package com.vivo.territory.Service.Email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmailTexto(String destinatario, String assunto, String mensagemHtml) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(mensagemHtml, true);

//            for (int i = 0; i < caminhosImagens.size(); i++) {
//                String caminhoImagem = caminhosImagens.get(i);
//                File file = new File(caminhoImagem);
//                if (file.exists()) {
//                    helper.addInline("imagemInline" + i, file);
//                } else {
//                    return "Erro ao tentar enviar email: Imagem não encontrada";
//                }
//            }


            javaMailSender.send(mimeMessage);
            return "Email enviado";
        } catch (Exception e) {
            return "Erro ao tentar enviar email: " + e.getLocalizedMessage();
        }
    }
}
