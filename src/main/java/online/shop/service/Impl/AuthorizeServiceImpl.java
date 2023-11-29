package online.shop.service.Impl;

import online.shop.component.Cart;
import online.shop.dto.RegisterDTO;
import online.shop.model.User;
import online.shop.repository.UserRepository;
import online.shop.service.AuthorizeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailServiceImpl mailService;

    public AuthorizeServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailServiceImpl mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public boolean login(String name, String password) {
        Optional<User> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword()) && user.getIsActive()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void registerUser(RegisterDTO registerDTO, HttpServletRequest request) throws Exception {
        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new Exception("User already exist");
        }

        User user = new User();
        Cart cart = new Cart();
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setSurname(registerDTO.getSurname());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setIsEmailVerified(Boolean.FALSE);
        userRepository.save(user);

        mailService.sendUserVerificationMail(user, request);
    }


}
