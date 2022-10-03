package ru.litvin.ev.virtuallibrary.data;

import org.springframework.data.repository.CrudRepository;
import ru.litvin.ev.virtuallibrary.LibraryUser;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Long> {
    LibraryUser findByUsername(String username);

}
