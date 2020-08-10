package com.is.teamTasker.controller;

import com.is.teamTasker.entity.Status;
import com.is.teamTasker.entity.User;
import com.is.teamTasker.jwt.JwtTokenProvider;
import com.is.teamTasker.model.AppResponse;
import com.is.teamTasker.model.AuthRequest;
import com.is.teamTasker.model.AuthResponse;
import com.is.teamTasker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("registration")
    public ResponseEntity registration(@RequestBody User user) {

        userRepository.save(new User(user.getUsername(), passwordEncoder.encode(user.getPassword()),
                user.getFirstname(), user.getLastname(), user.getEmail(), false, Status.INACTIVE, user.getRoles()));

        return ResponseEntity.ok(new AppResponse(1, "User created !"));
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthRequest request) {

        String client = jwtTokenProvider.getClient();

        if (!client.equals(request.getClientId())) {
            return ResponseEntity.status(403).build();
        }

        String username = request.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        User user = userRepository.findByUsernameAndEnabledAndStatus(username, true, Status.ACTIVE);

        if (user == null) {
            return ResponseEntity.ok(new AppResponse(1, "User is not active"));
        }

        String token = jwtTokenProvider.generateToken(user);

        AuthResponse response = new AuthResponse(username, token, jwtTokenProvider.getExpiry(), client);
        return ResponseEntity.ok(response);
    }

}
