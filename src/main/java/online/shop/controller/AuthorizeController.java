package online.shop.controller;

import lombok.AllArgsConstructor;
import online.shop.dto.RegisterDTO;
import online.shop.service.AuthorizeService;
import online.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping({"", "/"})
    //Отобразить главную страницу
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    // Обработать запрос на вход пользователя в систему (логин)
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        Model model) {
        if (authorizeService.login(name, password)) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "/login";
        }
    }

    @RequestMapping("/login-error")
    //Обработать ошибку входа пользователя в систему (логин)
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PostMapping("/register")
    //Зарегистрировать нового пользователя
    public void registerUser(@RequestBody RegisterDTO registerDTO, HttpServletRequest request) throws Exception {
        authorizeService.registerUser(registerDTO, request);
    }
}
