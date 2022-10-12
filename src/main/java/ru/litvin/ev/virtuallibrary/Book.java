package ru.litvin.ev.virtuallibrary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 2, max = 30, message = "The book title cannot be less than 2 characters and more than 30 characters")
    @NotBlank(message = "Title can't be empty")
    private String title;

    @Size(min = 5, max = 30, message = "The author's name cannot be less than 5 characters and more than 30 characters")
    @NotBlank(message = "Author can't be empty")
    private String author;
}
