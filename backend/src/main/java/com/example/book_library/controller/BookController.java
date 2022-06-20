package com.example.book_library.controller;

import com.example.book_library.dto.BookDto;
import com.example.book_library.mapper.BookDtoMapper;
import com.example.book_library.model.Book;
import com.example.book_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookDtoMapper mapper;

    private final BookService bookService;

    @Autowired
    public BookController(BookDtoMapper mapper, BookService bookService) {
        this.mapper = mapper;
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> findAllBooks() {
        return this.mapper.convertListToDtos(bookService.findAll());
    }

    @GetMapping(value = "/isbn/{isbn}")
    public BookDto findById(@PathVariable String isbn) {
        Book book = this.bookService.findById(isbn);
        return this.mapper.convertToDto(book);
    }

    @GetMapping
    @RequestMapping("/author/{id}")
    public List<BookDto> findByAuthor(@PathVariable Long id) {
        List<Book> booksByAuthor = this.bookService.findAllByAuthor(id);
        return this.mapper.convertListToDtos(booksByAuthor);
    }

    @GetMapping
    @RequestMapping("/genre/{name}")
    public List<BookDto> findByGenre(@PathVariable String name) {
        List<Book> booksByGenre = this.bookService.findAllByGenre(name);
        return this.mapper.convertListToDtos(booksByGenre);
    }

    @DeleteMapping(value = "/isbn/{isbn}")
    public void delete(@PathVariable String isbn) {
        this.bookService.delete(isbn);
    }

    @PostMapping
    public void create(@Valid @RequestBody BookDto bookDto) {
        Book book = this.mapper.convertToEntity(bookDto);
        this.bookService.add(book);
    }

    @PutMapping
    public void update(@Valid @RequestBody BookDto bookDto) {
        this.bookService.update(this.mapper.convertToEntity(bookDto));
    }
}
