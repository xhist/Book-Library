package com.example.book_library.repository;

import com.example.book_library.model.Book;
import com.example.book_library.model.ListBooks;
import com.example.book_library.model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface INoteRepository extends CrudRepository<Note, Long> {
    public List<Note> findAllByBook(Book book);
    public List<Note> findAllByList(ListBooks list);
    public Note findByBookAndList(Book book, ListBooks list);

    public boolean existsByBookAndList(Book book, ListBooks listBooks);
}
