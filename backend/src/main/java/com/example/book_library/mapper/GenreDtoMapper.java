package com.example.book_library.mapper;

import com.example.book_library.dto.BookDto;
import com.example.book_library.dto.GenreDto;
import com.example.book_library.model.Book;
import com.example.book_library.model.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreDtoMapper {
    public Genre convertToEntity(GenreDto genreDto) {
        Genre genre = new Genre(genreDto.getName());
        return genre;
    }

    public GenreDto convertToDto(Genre genre) {
        return new GenreDto(genre);
    }

    public List<GenreDto> convertListToDtos(List<Genre> genres) {
        return genres.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<Genre> convertListToEntities(List<GenreDto> genres) {
        return genres.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
