package com.br.apicerquilhotodos.controller;

import com.br.apicerquilhotodos.domain.user.User;
import com.br.apicerquilhotodos.dto.LoginRequestDTO;
import com.br.apicerquilhotodos.dto.RegisterRequestDTO;
import com.br.apicerquilhotodos.dto.ResponseDTO;
import com.br.apicerquilhotodos.infra.security.TokenService;
import com.br.apicerquilhotodos.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
//        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found!"));
//        if (passwordEncoder.matches(body.password(), user.getPassword())) {
//            String token = this.tokenService.generateToken(user);
//            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
//        }
//        return ResponseEntity.badRequest().build();
//    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body, HttpServletResponse response) {
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found!"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);

            // Adiciona o token como um cookie na resposta
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true); // Impede que o cookie seja acessado por JavaScript
            cookie.setMaxAge(60 * 60 * 24 * 7); // Define a validade do cookie (7 dias)
            cookie.setPath("/"); // Define o caminho do cookie como a raiz do domínio
            response.addCookie(cookie);

            // Retorna a resposta com o token no corpo
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setPhone(body.phone());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
