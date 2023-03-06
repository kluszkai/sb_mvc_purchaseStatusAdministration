package ik.sb_mvc_purchaseStatusAdministration.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final Log log = LogFactory.getLog(this.getClass());
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendRegistrationMessage(String email) {
        SimpleMailMessage message = null;

        try {
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);
            message.setTo(email);
            message.setSubject("Sikeres regisztráció");
            message.setText("Kedves " + email + " !" + "\n \n Köszönjük, hogy regisztráltál az oldalunkra!");
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("Hiba e-mail-küldésekor az alábbi címre: " + email + " " + e);
        }

    }


}
