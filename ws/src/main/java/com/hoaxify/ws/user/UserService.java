package com.hoaxify.ws.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoaxify.ws.email.EmailService;
import com.hoaxify.ws.user.exception.ActivationNotificationException;
import com.hoaxify.ws.user.exception.NotUniqueEmailException;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(rollbackOn = MailException.class)
    public void save(User user){

        try{
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setActivationToken(UUID.randomUUID().toString());
           userRepository.saveAndFlush(user);
           emailService.sendActivationEmail(user.getEmail(),user.getActivationToken());

        } catch(DataIntegrityViolationException ex){
            throw new NotUniqueEmailException();
        }catch (MailException ex){
            throw new ActivationNotificationException();
        }
       
    }



     
    
}
