package com.example.book_library.service;

import com.example.book_library.mapper.AuthorDtoMapper;
import com.example.book_library.model.Author;
import com.example.book_library.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class AuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private AuthorDtoMapper authorMapper;

    private final String NOT_FOUND_AUTHOR = "Author is not found!";

    public List<Author> findAll() {
        LinkedList<Author> authors = new LinkedList<>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_AUTHOR));
    }
}
