package com.example.book_library.mapper;

import com.example.book_library.dto.AuthorDto;
import com.example.book_library.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorDtoMapper {
    public Author convertToEntity(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getFirstname(), authorDto.getLastname());
    }

    public AuthorDto convertToAuthorDto(Author author) {
        return new AuthorDto(author);
    }

    public List<Author> convertListToEntities(List<AuthorDto> authorDtos) {
        return authorDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    public List<AuthorDto> convertListToDtos(List<Author> authors) {
        return authors.stream().map(this::convertToAuthorDto).collect(Collectors.toList());
    }
}
