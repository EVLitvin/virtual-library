package ru.litvin.ev.virtuallibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.litvin.ev.virtuallibrary.Book;
import ru.litvin.ev.virtuallibrary.data.BookRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/library")
public class LibraryController {

    final BookRepository bookRepo;

    public LibraryController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @ModelAttribute(name = "book")
    public Book book() {
        return new Book();
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
