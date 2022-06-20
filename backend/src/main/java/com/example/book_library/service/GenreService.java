package com.example.book_library.service;

import com.example.book_library.model.Genre;
import com.example.book_library.repository.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GenreService {
    @Autowired
    private IGenreRepository genreRepository;

    private final String NOT_FOUND_GENRE = "Genre is not found!";

    public List<Genre> findAll() {
        LinkedList<Genre> allGenres = new LinkedList<>();
        genreRepository.findAll().forEach(allGenres::add);
        return allGenres;
    }

    public Genre findById(String name) {
        return genreRepository.findById(name).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_GENRE));
    }

    public void add(Genre genre) {
        if(genreRepository.existsById(genre.getName()))
        {
            throw new IllegalArgumentException(
                    String.format("Genre %s already exists.", genre.getName())
            );
        }

        genreRepository.save(genre);
    }

    public void update(Genre genre) {
        if(!genreRepository.existsById(genre.getName()))
        {
            throw new NoSuchElementException(NOT_FOUND_GENRE);
        }

        genreRepository.save(genre);
    }

    @Transactional
    public void delete(String name) {
        if(!genreRepository.existsById(name))
        {
            throw new NoSuchElementException(NOT_FOUND_GENRE);
        }

        genreRepository.deleteById(name);
    }
}
