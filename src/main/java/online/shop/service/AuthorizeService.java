package online.shop.service;

import online.shop.dto.RegisterDTO;
import online.shop.model.User;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizeService {
    boolean login(User user);

    void registerUser(RegisterDTO registerDTO, HttpServletRequest request) throws Exception;


}
