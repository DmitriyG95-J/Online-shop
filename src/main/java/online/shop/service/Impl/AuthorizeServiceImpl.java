package online.shop.service.Impl;

import online.shop.dto.RegisterDTO;
import online.shop.model.User;
import online.shop.repository.UserRepository;
import online.shop.service.AuthorizeService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
    public boolean login(User user) {
        if (!user.getIsActive()) {
            return false;
        } else {return true;}

    }
    @Override
    public void registerUser(RegisterDTO registerDTO, HttpServletRequest request) throws Exception {
        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new Exception("User already exist");
        }

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setSurname(registerDTO.getSurname());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setIsEmailVerified(Boolean.FALSE);
        userRepository.save(user);

        mailService.sendUserVerificationMail(user, request);
    }


}
