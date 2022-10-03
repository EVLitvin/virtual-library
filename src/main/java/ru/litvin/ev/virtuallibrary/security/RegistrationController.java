package ru.litvin.ev.virtuallibrary.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.litvin.ev.virtuallibrary.data.LibraryUserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private LibraryUserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(LibraryUserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registrationForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
