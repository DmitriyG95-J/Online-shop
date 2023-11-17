package online.shop.service.Impl;

import lombok.AllArgsConstructor;
import online.shop.dto.RegisterDTO;
import online.shop.dto.UserDTO;
import online.shop.model.Role;
import online.shop.model.User;
import online.shop.repository.UserRepository;
import online.shop.service.MailService;
import online.shop.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new RuntimeException("password is not equals");
        }


        LocalDateTime currentDateTime = LocalDateTime.now();

        User user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .archive(false)
                .dateCreate(currentDateTime)
                .isActive(true)
                .isDeleted(false)
                .isEmailVerified(false)
                .build();
        userRepository.save(user);
        return true;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void registerUser(RegisterDTO registerDTO, HttpServletRequest request) throws Exception {
        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new Exception("User already exist");
        }
        MailService mailService = new MailServiceImpl();
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setSurname(registerDTO.getSurname());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setIsEmailVerified(Boolean.FALSE);
        userRepository.save(user);

        mailService.sendUserVerificationMail(user, request);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private UserDTO toDto(User user) {
        return UserDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDTO createUser(UserDTO userCreateReqDTO) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found with name: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles
        );
    }


}
