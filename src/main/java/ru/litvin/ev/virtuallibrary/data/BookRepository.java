package ru.litvin.ev.virtuallibrary.data;

import org.springframework.data.repository.CrudRepository;
import ru.litvin.ev.virtuallibrary.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
