package online.shop.service;

import online.shop.dto.RegisterDTO;
import online.shop.model.User;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizeService {
    boolean login(String name, String password);

    void registerUser(RegisterDTO registerDTO, HttpServletRequest request) throws Exception;


}
