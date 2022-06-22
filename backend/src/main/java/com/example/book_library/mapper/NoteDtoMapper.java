package com.example.book_library.mapper;

import com.example.book_library.dto.NoteDto;
import com.example.book_library.model.Book;
import com.example.book_library.model.ListBooks;
import com.example.book_library.model.Note;
import com.example.book_library.service.BookService;
import com.example.book_library.service.ListBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteDtoMapper {

    @Autowired
    private BookService bookService;

    @Autowired
    private ListBooksService listService;

    public Note convertToEntity(NoteDto noteDto) {
        Note note = new Note();
        Book book = bookService.findById(noteDto.getIsbn());
        ListBooks list = listService.findByName(noteDto.getListName());
        if(noteDto.getId() != null)
            note.setId(noteDto.getId());
        note.setBook(book);
        note.setList(list);
        return note;
    }

    public NoteDto convertToDto(Note note) {
        return new NoteDto(note);
    }

    public List<NoteDto> convertListToDtos(List<Note> notes) {
        return notes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<Note> convertListToEntities(List<NoteDto> noteDtos) {
        return noteDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
