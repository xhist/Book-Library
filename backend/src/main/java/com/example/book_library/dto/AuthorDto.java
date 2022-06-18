package com.example.book_library.dto;

import com.example.book_library.model.Author;
import com.example.book_library.model.Book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;

public class AuthorDto {
    private Long id;

    @Size(min = 2, message = "First name must be at least 2 characters long.")
    private String firstname;

    @Size(min = 2, message = "Last name must be at least 2 characters long.")
    private String lastname;

    public AuthorDto() {
    }

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.firstname = author.getFirstName();
        this.lastname = author.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
