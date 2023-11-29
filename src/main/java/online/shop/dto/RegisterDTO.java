package online.shop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
public class RegisterDTO {
    @NotBlank
    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Имя должно состоять из букв")
    private String name;

    @NotBlank
    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Фамилия должна состоять из букв")
    private String surname;

    @NotBlank
    @Size(min = 1, max = 24, message = "Пароль должен быть больше 8 символов, но меньше 24")
    private String password;

    @Email
    private String email;
}
