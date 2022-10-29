package ru.litvin.ev.virtuallibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.litvin.ev.virtuallibrary.LibraryUser;
import ru.litvin.ev.virtuallibrary.data.LibraryUserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/user-profile")
public class LibraryUserProfileController {

    LibraryUserRepository libraryUserRepo;

    public LibraryUserProfileController(LibraryUserRepository libraryUserRepo) {
        this.libraryUserRepo = libraryUserRepo;
    }

    @ModelAttribute(name = "libraryUser")
    public LibraryUser libraryUser(Principal principal) {
        String username = principal.getName();
        return libraryUserRepo.findByUserNickname(username);
    }

    @GetMapping
    public String showUserPage(){
        return "user-profile";
    }
}
