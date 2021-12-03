package com.andreasmarsh.SpringTest;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

@Service
public class CreditCardServices {

    @Autowired
    private UserRepository repo;

    @Autowired
    private CreditCardRepository repo3;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> listAll() {
        return repo.findAll();
    }


    public String secretCardEncoder(String cvv) {

        // Encode into Base64 format
        String str = Base64.getEncoder().encodeToString(cvv.getBytes());

        return str;
    }

    public String secretCardDecoder(String cvv) {

        // decode into String from encoded format
        byte[] actualByte = Base64.getDecoder()
                .decode(cvv);

        cvv = new String(actualByte);

        return cvv;
    }


}