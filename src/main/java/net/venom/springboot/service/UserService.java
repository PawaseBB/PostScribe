package net.venom.springboot.service;

import net.venom.springboot.dto.RegistrationDto;
import net.venom.springboot.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
