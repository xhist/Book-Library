package com.example.book_library.service;

import com.example.book_library.model.Author;
import com.example.book_library.model.Book;
import com.example.book_library.model.Genre;
import com.example.book_library.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepository;
    
    @Autowired
    private GenreService genreService;

    @Autowired
    private AuthorService authorService;

    private final String NOT_FOUND_BOOK = "Book is not found!";

    public List<Book> findAll() {
        LinkedList<Book> allBooks = new LinkedList<>();
        bookRepository.findAll().forEach(allBooks::add);
        return allBooks;
    }

    public List<Book> findAllByAuthor(Long id) {
        LinkedList<Book> allBooks = new LinkedList<>();
        Author author = authorService.findById(id);
        bookRepository.findAllByAuthor(author).forEach(allBooks::add);
        return allBooks;
    }

    public List<Book> findAllByGenre(String name) {
        LinkedList<Book> allBooks = new LinkedList<>();
        Genre genre = genreService.findById(name);
        return bookRepository.findAllByGenresContains(genre);
    }

    public Book findById(String isbn) {
        return bookRepository.findById(isbn).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_BOOK));
    }

    public void add(Book book) {
        if(bookRepository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException(
                    String.format("Book %s already exists.", book.getTitle())
            );
        }

        bookRepository.save(book);
    }

    public void update(Book book) {
        if(!bookRepository.existsById(book.getIsbn())) {
            throw new NoSuchElementException(NOT_FOUND_BOOK);
        }

        bookRepository.save(book);
    }

    public void delete(String isbn) {
        if(!bookRepository.existsById(isbn)) {
            throw new NoSuchElementException(NOT_FOUND_BOOK);
        }

        bookRepository.deleteById(isbn);
    }
}
