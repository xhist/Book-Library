package com.example.book_library.dto;

import com.example.book_library.model.Author;
import com.example.book_library.model.Book;
import com.example.book_library.model.Genre;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Set;

public class BookDto {
    @Size(min = 2, message = "The title must consist of at least 2 characters")
    private String title;

    @NotNull(message = "Book must have genres")
    private Set<Genre> genres;

    @Size(min = 10, max = 13, message = "ISBN number must consist of 10-13 characters")
    private String isbn;

    @NotNull(message = "Author must not be null")
    private Author author;

    public BookDto() {
    }

    public BookDto(Book book) {
        this.title = book.getTitle();
        this.genres = book.getGenres();
        this.isbn = book.getIsbn();
        this.author = book.getAuthor();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
