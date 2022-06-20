package com.example.book_library.repository;

import com.example.book_library.model.Author;
import com.example.book_library.model.Book;
import com.example.book_library.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBookRepository extends CrudRepository<Book, String> {
    public List<Book> findAllByAuthor(Author author);
    public List<Book> findAllByGenresContains(Genre genre);
    public boolean existsByIsbn(String isbn);
}
