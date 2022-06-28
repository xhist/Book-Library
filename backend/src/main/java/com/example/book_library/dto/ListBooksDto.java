package com.example.book_library.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ListBooksDto {

    @NotNull
    @NotBlank
    private String name;

    public ListBooksDto() {}

    public ListBooksDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
