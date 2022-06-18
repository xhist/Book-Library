package com.example.book_library.repository;

import com.example.book_library.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface IAuthorRepository extends CrudRepository<Author, Long> {
}
