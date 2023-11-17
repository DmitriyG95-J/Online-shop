package online.shop.controller;


import lombok.AllArgsConstructor;
import online.shop.dto.UserDTO;
import online.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
   private final UserService userService;

   @GetMapping
   public String userList(Model model) {
       model.addAttribute("users", userService.getAll());
       return "userList";
   }

   @GetMapping("/new")
    public String newUser(Model model) {
       model.addAttribute("user", new UserDTO());
       return "user";
   }

   @PostMapping("/new")
    public String saveUser(UserDTO dto, Model model) {
       if (userService.save(dto)) {
           return "redirect:/users";
       } else {
           model.addAttribute("user", dto);
           return "user";
       }
   }
}
