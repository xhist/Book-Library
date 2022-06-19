package com.example.book_library.controller;

import com.example.book_library.dto.AuthorDto;
import com.example.book_library.mapper.AuthorDtoMapper;
import com.example.book_library.model.Author;
import com.example.book_library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorDtoMapper mapper;

    @Autowired
    public AuthorController(AuthorDtoMapper mapper, AuthorService authorService) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public AuthorDto findById(@PathVariable Long id) {
        Author author = authorService.findById(id);
        return mapper.convertToAuthorDto(author);
    }

    @GetMapping
    public List<AuthorDto> findAll() {
        return mapper.convertListToDtos(authorService.findAll());
    }

    @PostMapping
    public void create(@Valid @RequestBody AuthorDto authorDto) {
        authorService.add(mapper.convertToEntity(authorDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        authorService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody AuthorDto authorDto) {
        authorService.update(mapper.convertToEntity(authorDto));
    }
}
