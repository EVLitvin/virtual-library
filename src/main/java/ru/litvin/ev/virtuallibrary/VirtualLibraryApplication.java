package ru.litvin.ev.virtuallibrary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.litvin.ev.virtuallibrary.data.BookRepository;

@SpringBootApplication
public class VirtualLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualLibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(BookRepository bookRepo) {
        return args -> {
            bookRepo.save(new Book(1L, "War and Peace", "Leo Tolstoy"));
            bookRepo.save(new Book(2L, "Capital", "Karl Marks"));
        };
    }

}
