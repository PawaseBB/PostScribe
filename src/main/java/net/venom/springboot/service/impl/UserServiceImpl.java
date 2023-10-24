package net.venom.springboot.service.impl;

import net.venom.springboot.dto.RegistrationDto;
import net.venom.springboot.entity.Role;
import net.venom.springboot.entity.User;
import net.venom.springboot.repository.RoleRepository;
import net.venom.springboot.repository.UserRepository;
import net.venom.springboot.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user =new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());

        // use Spring security to encrypt the password
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role= roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
