package com.example.shoppingweb.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    private static JavaMailSender javaMailSenderStatic;


    public static void sendEmail(String email, String subject, String content) throws MessagingException {
        System.out.println("aaa");
        MimeMessage mimeMessage = javaMailSenderStatic.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(content);

        javaMailSenderStatic.send(mimeMessage);
    }

    @Autowired
    public void setJavaMailSenderStatic(JavaMailSender javaMailSenderStatic) {
        EmailUtil.javaMailSenderStatic = javaMailSenderStatic;
    }
}
