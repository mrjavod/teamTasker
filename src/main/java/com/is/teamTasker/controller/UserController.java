package com.is.teamTasker.controller;

import com.is.teamTasker.entity.Status;
import com.is.teamTasker.entity.User;
import com.is.teamTasker.mailer.MailSender;
import com.is.teamTasker.model.AppResponse;
import com.is.teamTasker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin/user/")
public class UserController {

    private UserRepository userRepository;
    private JavaMailSender javaMailSender;

    @Autowired
    public UserController(UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping("getAll")
    public ResponseEntity getUsers() {

        return ResponseEntity.ok(new AppResponse(1, "OK", userRepository.findAll()));
    }

    @GetMapping("getInactiveUsers")
    public ResponseEntity getInactiveUsers() {

        List<User> list = userRepository.findAllByEnabledAndStatus(false, Status.INACTIVE);

        return ResponseEntity.ok(new AppResponse(1, "OK", list));
    }

    @GetMapping("getActiveUsers")
    public ResponseEntity getActiveUsers() {

        List<User> list = userRepository.findAllByEnabledAndStatus(true, Status.ACTIVE);

        return ResponseEntity.ok(new AppResponse(1, "OK", list));
    }


    @GetMapping("enabled/{userId}")
    public ResponseEntity enabled(@PathVariable(name = "userId") UUID userId) {

        User user = userRepository.findByUserId(userId);
        user.setEnabled(true);
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);

        MailSender mailSender = new MailSender(javaMailSender);
        mailSender.sendSimpleMessage("javod1995@gmail.com", "TeamTasker Admin",
                "Sizga tizimdan foydalanishga ruhsat berildi !");

        return ResponseEntity.ok(new AppResponse(1, "User activated"));
    }

    @GetMapping("blocked/{userId}")
    public ResponseEntity blocked(@PathVariable(name = "userId") UUID userId) {

        User user = userRepository.findByUserId(userId);
        user.setEnabled(false);
        user.setStatus(Status.INACTIVE);

        userRepository.save(user);

        MailSender mailSender = new MailSender(javaMailSender);
        mailSender.sendSimpleMessage("javod1995@gmail.com", "TeamTasker Admin",
                "Sizdan tizimdan foylanish huquqi o'chirildi !");

        return ResponseEntity.ok(new AppResponse(1, "User blocked"));
    }

    @GetMapping("get/{userId}")
    public ResponseEntity get(@PathVariable(name = "userId") UUID userId) {

        User user = userRepository.getUserByUserId(userId);

        return ResponseEntity.ok(new AppResponse(1, "OK", user));
    }


}
