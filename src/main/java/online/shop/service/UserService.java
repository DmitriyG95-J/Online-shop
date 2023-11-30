package online.shop.service;

import online.shop.dto.RegisterDTO;
import online.shop.dto.UserDTO;
import online.shop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);

    List<UserDTO> getAll();
}
