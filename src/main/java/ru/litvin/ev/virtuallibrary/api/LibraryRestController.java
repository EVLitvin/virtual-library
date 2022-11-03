package ru.litvin.ev.virtuallibrary.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.litvin.ev.virtuallibrary.Book;
import ru.litvin.ev.virtuallibrary.data.BookRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/books", produces = "application/json")
@CrossOrigin(origins = "http://virtuallibrary:8080")
public class LibraryRestController {

    private final BookRepository bookRepo;

    public LibraryRestController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping(params = "all")
    public Iterable<Book> showAllBooks() {
        return bookRepo.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable("id") Long id) {
        Optional<Book> optionalBook = bookRepo.findBookById(id);
        if (optionalBook.isPresent()) {
            return new ResponseEntity<>(optionalBook.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Book> findByTitle(@PathVariable("title") String title) {
        Optional<Book> optionalBook = bookRepo.findBookByTitleIgnoreCase(title);
        if (optionalBook.isPresent()) {
            return new ResponseEntity<>(optionalBook.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<Book> findByAuthor(@PathVariable("author") String author) {
        Optional<Book> optionalBook = bookRepo.findBookByAuthorIgnoreCase(author);
        if (optionalBook.isPresent()) {
            return new ResponseEntity<>(optionalBook.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Book postBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @PatchMapping(path = "/{bookTitle}", consumes = "application/json")
    public Book patchBook(@PathVariable("bookTitle") String bookTitle, @RequestBody Book patch) {
        Book book = bookRepo.findBookByTitleIgnoreCase(bookTitle).get();
        if (patch.getTitle() != null) {
            book.setTitle(patch.getTitle());
        }
        if(patch.getAuthor()!= null) {
            book.setAuthor(patch.getAuthor());
        }
        return bookRepo.save(book);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable("id") Long id) {
        try {
            bookRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }

}