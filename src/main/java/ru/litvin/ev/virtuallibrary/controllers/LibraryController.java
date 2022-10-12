package ru.litvin.ev.virtuallibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.litvin.ev.virtuallibrary.Book;
import ru.litvin.ev.virtuallibrary.LibraryUser;
import ru.litvin.ev.virtuallibrary.data.BookRepository;
import ru.litvin.ev.virtuallibrary.data.LibraryUserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/library")
@SessionAttributes("order")
public class LibraryController {

    final BookRepository bookRepo;
    final LibraryUserRepository libraryUserRepo;

    public LibraryController(BookRepository bookRepo, LibraryUserRepository libraryUserRepo) {
        this.bookRepo = bookRepo;
        this.libraryUserRepo = libraryUserRepo;
    }

    @ModelAttribute(name = "book")
    public Book book() {
        return new Book();
    }

    @ModelAttribute(name = "libraryUser")
    public LibraryUser libraryUser(Principal principal) {
        String username = principal.getName();
        return libraryUserRepo.findByUsername(username);
    }

    @ModelAttribute(name = "date")
    public String date() {
        String pattern = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    @GetMapping
    public String showLibraryPage(Model model) {
        model.addAttribute("allBooks", bookRepo.findAll());
        return "library";
    }

    @PostMapping
    public String donateBookToLibrary(@Valid Book book, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("allBooks", bookRepo.findAll());
            return "library";
        }
        bookRepo.save(book);
        return "redirect:/library";
    }
}
