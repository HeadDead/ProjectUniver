package com.example.ProjectUniver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String recipient) throws MessagingException {
        String token = UUID.randomUUID().toString();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipient);
        helper.setSubject("Подтверждение регистрации");

        String text = "<p>Спасибо за регистрацию на нашем сайте!</p>"
                + "<p>Для подтверждения вашей учетной записи, перейдите по ссылке ниже:</p>"
                + "<a href='http://localhost:8080/confirm-account?token=" + token + "'>Подтвердить</a>"
                + "<p>С уважением, наш сайт</p>";

        helper.setText(text, true);
        javaMailSender.send(message);
    }
}




