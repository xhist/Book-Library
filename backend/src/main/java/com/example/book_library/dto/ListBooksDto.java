package com.example.book_library.dto;

import com.example.book_library.mapper.NoteDtoMapper;
import com.example.book_library.model.ListBooks;
import com.example.book_library.model.Note;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListBooksDto {

    @Autowired
    private NoteDtoMapper noteMapper;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Set<NoteDto> notes;

    public ListBooksDto() {}

    public ListBooksDto(ListBooks list) {
        this.name = list.getName();
        List<Note> notes = new ArrayList<Note>(list.getNotes());
        List<NoteDto> noteDtos = this.noteMapper.convertListToDtos(notes);
        Set<NoteDto> setNoteDtos = new HashSet<>(noteDtos);
        this.notes = setNoteDtos;
    }

    public ListBooksDto(String name, Set<NoteDto> notes) {
        this.name = name;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NoteDto> getNoteDtos() {
        return notes;
    }

    public void setNoteDtos(Set<NoteDto> notes) {
        this.notes = notes;
    }
}
