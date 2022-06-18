package com.example.book_library.repository;

import com.example.book_library.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface IGenreRepository extends CrudRepository<Genre, String> {
}
