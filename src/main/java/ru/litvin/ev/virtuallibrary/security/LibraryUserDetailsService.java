package ru.litvin.ev.virtuallibrary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.litvin.ev.virtuallibrary.LibraryUser;
import ru.litvin.ev.virtuallibrary.data.LibraryUserRepository;

@Service
public class LibraryUserDetailsService
        implements org.springframework.security.core.userdetails.UserDetailsService {

    private LibraryUserRepository userRepo;

    @Autowired
    public LibraryUserDetailsService(LibraryUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        LibraryUser user = userRepo.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }

}