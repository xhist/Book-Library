package com.example.book_library.service;

import com.example.book_library.model.Author;
import com.example.book_library.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    private final String NOT_FOUND_AUTHOR = "Author is not found!";

    public List<Author> findAll() {
        LinkedList<Author> authors = new LinkedList<>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_AUTHOR));
    }

    public void add(Author author) {
        if(authorRepository.existsById(author.getId())) {
            throw new IllegalArgumentException(
                    String.format("Author %s %s already exists.", author.getFirstName(), author.getLastName())
            );
        }

        authorRepository.save(author);
    }

    public void update(Author author) {
        if(!authorRepository.existsById(author.getId()))
        {
            throw new NoSuchElementException(NOT_FOUND_AUTHOR);
        }

        authorRepository.save(author);
    }

    public void delete(Long id) {
        if(!authorRepository.existsById(id))
        {
            throw new NoSuchElementException(NOT_FOUND_AUTHOR);
        }

        authorRepository.deleteById(id);
    }
}
