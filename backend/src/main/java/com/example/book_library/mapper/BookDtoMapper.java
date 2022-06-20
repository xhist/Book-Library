package com.example.book_library.mapper;

import com.example.book_library.dto.BookDto;
import com.example.book_library.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDtoMapper {
    public Book convertToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenres(bookDto.getGenres());

        return book;
    }

    public BookDto convertToDto(Book book) {
        return new BookDto(book);
    }

    public List<BookDto> convertListToDtos(List<Book> books) {
        return books.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<Book> convertListToEntities(List<BookDto> books) {
        return books.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
