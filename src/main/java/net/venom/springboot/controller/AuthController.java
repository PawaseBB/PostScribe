package net.venom.springboot.controller;

import jakarta.validation.Valid;
import net.venom.springboot.dto.RegistrationDto;
import net.venom.springboot.entity.User;
import net.venom.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }



    // handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistration(Model model){

        // this object holds form data
        RegistrationDto user= new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submitted request
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result,
                           Model model){
        User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser != null){
            result.rejectValue("email", null, "This Email ID is already in use by another user, please try with other Email id.");
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }


    // handler method to handle login page request
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
