package com.andreasmarsh.SpringTest;

import java.io.UnsupportedEncodingException;
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
public class MovieServices {

    @Autowired
    private MovieRepository repo;

    @Autowired
    private ReviewRepository repo2;

    /**
    public List<User> listAll() {
        return repo.findAll();
    }
     */

    public void addMovie(Movie movie, Review review, String siteURL)
            throws UnsupportedEncodingException, MessagingException {

        //save the movies to the database
        repo.save(movie);
        repo2.save(review);

    }


}