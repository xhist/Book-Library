package com.example.book_library.service;

import com.example.book_library.mapper.GenreDtoMapper;
import com.example.book_library.model.Genre;
import com.example.book_library.repository.IGenreRepository;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class GenreService {
    @Autowired
    private IGenreRepository genreRepository;

    @Autowired
    private GenreDtoMapper genreMapper;

    private final String NOT_FOUND_GENRE = "Genre is not found!";

    public List<Genre> findAll() {
        LinkedList<Genre> allGenres = new LinkedList<>();
        genreRepository.findAll().forEach(allGenres::add);
        return allGenres;
    }

    public Genre findById(String name) {
        return genreRepository.findById(name).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_GENRE));
    }
}
