package com.example.project.controllers;

import com.example.project.dtos.request.ForgotPasswordDto;
import com.example.project.dtos.request.LoginDto;
import com.example.project.dtos.request.RegisterDto;
import com.example.project.dtos.request.ResetPasswordDto;
import com.example.project.dtos.response.AuthResponseDto;
import com.example.project.models.Role;
import com.example.project.models.Token;
import com.example.project.models.UserEntity;
import com.example.project.repositories.RoleRepository;
import com.example.project.repositories.TokenRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.security.JWTGenerator;
import com.example.project.services.SendMailService;
import com.example.project.untils.TokenUntils;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JWTGenerator jwtGenerator;
    private final TokenRepository tokenRepository;
    private final SendMailService mailService;

    private final String clientUrl = System.getenv("CLIENT_URL");

    @PostMapping(path = "register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsUserByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setVerify(false);
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role newUserRole = new Role();
                    newUserRole.setName("USER");
                    return roleRepository.save(newUserRole);
                });
        user.setRoles(Collections.singletonList(userRole));

        userRepository.save(user);

        String tokenValue = TokenUntils.generateToken();

        Token token = new Token();

        token.setValue(tokenValue);
        token.setUser(user);
        token.setExpiryDate(LocalDateTime.now().plusDays(1));

        tokenRepository.save(token);

        String subject = "Verify account";
        String message = "<a href='" + clientUrl + "/verify-account?token=" + token.getValue()
                + "' target='_blank'>Click here to verify your account</a>";

        mailService.sendMail(registerDto.getEmail(), subject, message);


        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PostMapping(path = "login")
    public ResponseEntity<?> login(
            @RequestBody LoginDto loginDto
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);


        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setEmail(loginDto.getEmail());
        authResponseDto.setAccessToken(token);

        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @PostMapping(path = "forgot-password")
    public ResponseEntity<String> forgot(
            @RequestBody ForgotPasswordDto forgotPasswordDto
    ) {
        if (!userRepository.existsUserByEmail(forgotPasswordDto.getEmail())) {
            return new ResponseEntity<>("Email not found!", HttpStatus.NOT_FOUND);
        }

        UserEntity user = userRepository.findByEmail(forgotPasswordDto.getEmail())
                .orElseThrow(() -> new ExpressionException("User not found"));

        Token exitToken = tokenRepository.findByUserId(user.getId()).orElse(null);
        if (exitToken == null || exitToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            String value = TokenUntils.generateToken();
            Token token = new Token();

            token.setValue(value);
            token.setUser(user);
            token.setExpiryDate(LocalDateTime.now().plusDays(1));

            tokenRepository.save(token);

            String subject = "Forgot password";
            String message = "<a href='" + clientUrl + "/reset-password?token=" + token.getValue()
                    + "' target='_blank'>Click here to reset your password</a>";


            mailService.sendMail(forgotPasswordDto.getEmail(), subject, message);
            return new ResponseEntity<>("New Token send successfully", HttpStatus.OK);
        } else {
            String subject = "Forgot password";
            String message =
                    "<a href='http://localhost:8080/reset-password?token=" + exitToken.getValue()
                            + "' " + "target='_blank'>Click here to reset your password</a>";

            mailService.sendMail(forgotPasswordDto.getEmail(), subject, message);
            return new ResponseEntity<>("Token send successfully", HttpStatus.OK);
        }
    }

    @PutMapping(path = "reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam("token") String token,
            @RequestBody ResetPasswordDto resetPasswordDto
    ) {
        if (!tokenRepository.existsByValue(token)) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }

        Token exitToken = tokenRepository.findByValue(token)
                .orElseThrow(() -> new ExpressionException("Token not found"));

        if (!exitToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            UserEntity user = userRepository.findById(exitToken.getUser().getId())
                    .orElseThrow(() -> new ExpressionException("User not found"));

            user.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
            user.setToken(null);
            userRepository.save(user);

            tokenRepository.delete(exitToken);

            return ResponseEntity.ok("Reset password successfully!");
        }

        return new ResponseEntity<>("Token out date", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "verify-account")
    private ResponseEntity<String> verifyAccount(
            @RequestParam("token") String token
    ) {
        if (!tokenRepository.existsByValue(token)) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }

        Token exitToken = tokenRepository.findByValue(token)
                .orElseThrow(() -> new ExpressionException("Token not found"));

        if (!exitToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            UserEntity user = userRepository.findById(exitToken.getUser().getId())
                    .orElseThrow(() -> new ExpressionException("User not found"));

            user.setVerify(true);
            user.setToken(null);
            userRepository.save(user);

            tokenRepository.delete(exitToken);

            return ResponseEntity.ok("Verify account successfully");
        }

        return new ResponseEntity<>("Token out date", HttpStatus.BAD_REQUEST);
    }


}
