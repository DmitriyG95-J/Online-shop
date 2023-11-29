package online.shop.controller;


import lombok.AllArgsConstructor;
import online.shop.dto.RegisterDTO;
import online.shop.dto.UserDTO;
import online.shop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getAllUsers")
    @ResponseStatus(HttpStatus.OK)
    //Получить список всех пользователей
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    //Создать нового пользователя
    public UserDTO newUser() {
        return new UserDTO();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    //Сохранить пользователя
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserDTO userDto) {
        if (userService.save(userDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}



