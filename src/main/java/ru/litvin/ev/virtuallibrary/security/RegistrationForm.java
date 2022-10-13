package ru.litvin.ev.virtuallibrary.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.litvin.ev.virtuallibrary.LibraryUser;

@Data
public class RegistrationForm {

    private final String userNickname;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;

    public LibraryUser toUser(PasswordEncoder passwordEncoder) {
        return new LibraryUser(userNickname, passwordEncoder.encode(password), firstName, lastName, email, phoneNumber);
    }

}
