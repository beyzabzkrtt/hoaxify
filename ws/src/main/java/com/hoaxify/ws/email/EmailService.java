package com.hoaxify.ws.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hoaxify.ws.configuration.HoaxifyProperties;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;

    @Autowired
    HoaxifyProperties hoaxifyProperties;

    @PostConstruct
    public void initiliaze() {

        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(hoaxifyProperties.getEmail().host());
        mailSender.setPort(hoaxifyProperties.getEmail().port());
        mailSender.setUsername(hoaxifyProperties.getEmail().username());
        mailSender.setPassword(hoaxifyProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
      

    }

    String activationEmail = """
            <html>
                <body>
                    <h1>Activate Account</h1>
                    <a href="${url}"> Click Here </a>
                </body>
            </html>        
            """;

    public void sendActivationEmail(String email,String activationToken) {

        var activationUrl = hoaxifyProperties.getClient().host() + "/activation/" + activationToken;
        var mailBody = activationEmail.replace("${url}", activationUrl);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        

        try {
            message.setFrom(hoaxifyProperties.getEmail().from());
            message.setTo(email);
            message.setSubject("Account Activation");
            message.setText(mailBody,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        this.mailSender.send(mimeMessage);
    }

}
