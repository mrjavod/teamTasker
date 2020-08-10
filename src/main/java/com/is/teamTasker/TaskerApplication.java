package com.is.teamTasker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class TaskerApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaskerApplication.class, args);
    }

    @Autowired
    private JavaMailSender javaMailSender;
}
