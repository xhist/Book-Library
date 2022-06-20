package com.example.book_library.dto;

import com.example.book_library.model.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GenreDto {
    @NotNull(message = "Genre name can't be null!")
    @NotBlank(message = "Genre name can't be empty!")
    @Size(min = 2, message = "Genre name must be at least 2 chars long!")
    private String name;

    public GenreDto() {}

    public GenreDto(String name) {
        setName(name);
    }

    public GenreDto(Genre genre) {
        setName(genre.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.length() < 2)
            throw new IllegalArgumentException("Genre name must be at least 2 chars long!");
        this.name = name;
    }
}
