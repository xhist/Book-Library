package com.example.book_library.controller;

import com.example.book_library.dto.NoteDto;
import com.example.book_library.dto.NoteDtosWrapper;
import com.example.book_library.mapper.NoteDtoMapper;
import com.example.book_library.model.Note;
import com.example.book_library.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;
    private NoteDtoMapper mapper;

    @Autowired
    public NoteController(NoteService noteService, NoteDtoMapper mapper) {
        this.noteService = noteService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<NoteDto> findAll() {
        return this.mapper.convertListToDtos(this.noteService.findAll());
    }

    @GetMapping("/id/{id}")
    public NoteDto findById(@PathVariable Long id) {
        return this.mapper.convertToDto(this.noteService.findById(id));
    }

    @GetMapping("/list/{name}")
    public List<NoteDto> findByList(@PathVariable String name) {
        return this.mapper.convertListToDtos(this.noteService.findByList(name));
    }

    @GetMapping("/book/{isbn}")
    public List<NoteDto> findByBook(@PathVariable String isbn) {
        return this.mapper.convertListToDtos(this.noteService.findByBook(isbn));
    }

    @GetMapping(value = {"/book/{isbn}/list/{name}", "/list/{name}/book/{isbn}"})
    public NoteDto findByBookAndList(@PathVariable String isbn, @PathVariable String name) {
        return this.mapper.convertToDto(this.noteService.findByBookAndList(isbn, name));
    }

    @PostMapping("/multiple")
    public void addAll(@Valid @RequestBody NoteDtosWrapper noteDtosWrapper) {
        List<Note> notes = this.mapper.convertListToEntities(noteDtosWrapper.getNoteDtos());
        for(Note note : notes)
            this.noteService.add(note);
    }

    @PostMapping
    public void add(@Valid @RequestBody NoteDto noteDto) {
        this.noteService.add(this.mapper.convertToEntity(noteDto));
    }

    @PutMapping
    public void update(@Valid @RequestBody NoteDto noteDto) {
        this.noteService.update(this.mapper.convertToEntity(noteDto));
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable Long id) {
        this.noteService.delete(id);
    }

    @DeleteMapping(value = {"/book/{isbn}/list/{name}", "/list/{name}/book/{isbn}"})
    public void deleteByBookAndList(@PathVariable String isbn, @PathVariable String name) {
        this.noteService.delete(this.noteService.findByBookAndList(isbn, name).getId());
    }

    @DeleteMapping("/multiple")
    public void deleteMultiple(@Valid @RequestBody List<NoteDto> notes) {
        for(NoteDto note: notes) {
            this.noteService.delete(this.noteService.findByBookAndList(note.getIsbn(), note.getListName()).getId());
        }
    }
}
