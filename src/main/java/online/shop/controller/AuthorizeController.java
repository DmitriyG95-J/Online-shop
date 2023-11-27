package online.shop.controller;

import lombok.AllArgsConstructor;
import online.shop.dto.RegisterDTO;
import online.shop.service.AuthorizeService;
import online.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class AuthorizeController {


    private final AuthorizeService authorizeService;


    @RequestMapping({"", "/"})
    public String index() {
    return "index";
    }

    @PostMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterDTO registerDTO, HttpServletRequest request) throws Exception {
        authorizeService.registerUser(registerDTO, request);
    }




}
