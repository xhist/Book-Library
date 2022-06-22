package com.example.book_library.repository;

import com.example.book_library.model.ListBooks;
import org.springframework.data.repository.CrudRepository;

public interface IListBooksRepository extends CrudRepository<ListBooks, String> {
}
