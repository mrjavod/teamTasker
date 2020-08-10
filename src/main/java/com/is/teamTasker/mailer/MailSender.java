package com.is.teamTasker.mailer;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSender {

    @Value("${spring.mail.username}")
    private String USERNAME;

    private JavaMailSender emailSender;

    public MailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String recipientEmail, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USERNAME);
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);

        System.out.println("SUCCESSFUL SEND");
    }

}
