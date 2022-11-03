package ru.litvin.ev.virtuallibrary.data;

import org.springframework.data.repository.CrudRepository;
import ru.litvin.ev.virtuallibrary.Book;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findBookById(Long id);

    Optional<Book> findBookByTitleIgnoreCase(String title);

    Optional<Book> findBookByAuthorIgnoreCase(String author);

    void deleteById(Long id);

}
