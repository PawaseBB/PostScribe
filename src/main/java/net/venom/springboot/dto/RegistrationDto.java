package net.venom.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private  Long id;

    @NotEmpty
    private  String firstName;
    @NotEmpty(message = "Last name should not be empty")
    private  String lastName;
    @NotEmpty(message = "Email should not be empty or null")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
