package com.example.book_library.dto;

import com.example.book_library.model.Book;
import com.example.book_library.model.Note;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class NoteDto {
    private Long id;

    @NotNull
    private String description;

    @NotNull
    @NotBlank
    private String isbn;

    @NotNull
    @NotBlank
    private String listName;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public NoteDto() {}

    public NoteDto(Note note) {
        this.id = note.getId();
        this.description = "";
        this.isbn = note.getBook().getIsbn();
        this.listName = note.getList().getName();
    }

    public NoteDto(Long id, String description, String isbn, String listName) {
        this.id = id;
        this.description = description;
        this.isbn = isbn;
        this.listName = listName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
