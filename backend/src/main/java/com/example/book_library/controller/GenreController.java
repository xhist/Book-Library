package com.example.book_library.controller;

import com.example.book_library.dto.GenreDto;
import com.example.book_library.mapper.GenreDtoMapper;
import com.example.book_library.model.Genre;
import com.example.book_library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;
    private final GenreDtoMapper mapper;

    @Autowired
    public GenreController(GenreDtoMapper mapper, GenreService genreService) {
        this.mapper = mapper;
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDto> findAll() {
        return this.mapper.convertListToDtos(genreService.findAll());
    }

    @GetMapping("/{name}")
    public GenreDto findById(@PathVariable String name) {
        return this.mapper.convertToDto(genreService.findById(name));
    }

    @PostMapping
    public void create(@Valid @RequestBody GenreDto genreDto) {
        Genre genre = this.mapper.convertToEntity(genreDto);
        genreService.add(genre);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        genreService.delete(name);
    }
}
