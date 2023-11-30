package online.shop.controller;

import lombok.AllArgsConstructor;
import online.shop.dto.LoginDTO;
import online.shop.dto.RegisterDTO;
import online.shop.service.AuthorizeService;
import online.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/authorize")
public class AuthorizeController {

    private final AuthorizeService authorizeService;

    @Autowired
    public AuthorizeController(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }

    @PostMapping("/login")
    // Обработать запрос на вход пользователя в систему (логин)
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        if (authorizeService.login(loginDTO.getName(), loginDTO.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    //Зарегистрировать нового пользователя
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO, HttpServletRequest request) throws Exception {
        authorizeService.registerUser(registerDTO, request);
        return ResponseEntity.ok("User registered successfully");
    }
}
