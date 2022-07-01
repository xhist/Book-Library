package com.example.book_library.service;

import com.example.book_library.mapper.NoteDtoMapper;
import com.example.book_library.model.Book;
import com.example.book_library.model.ListBooks;
import com.example.book_library.model.Note;
import com.example.book_library.repository.INoteRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {
    @Autowired
    private INoteRepository noteRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private ListBooksService listService;

    private final String NOT_FOUND_NOTE = "Note not found!";

    public List<Note> findAll() {
        LinkedList<Note> notes = new LinkedList<>();
        noteRepository.findAll().forEach(notes::add);
        return notes;
    }

    public List<Note> findByBook(String isbn) {
        return noteRepository.findAllByBook(this.bookService.findById(isbn));
    }

    public List<Note> findByList(String name) {
        return noteRepository.findAllByList(this.listService.findByName(name));
    }

    public void update(Note note) {
        if (note.getId() != null && !noteRepository.existsById(note.getId())) {
            throw new NoSuchElementException(NOT_FOUND_NOTE);
        }
        if(!noteRepository.existsByBookAndList(note.getBook(), note.getList())) {
            throw new NoSuchElementException(NOT_FOUND_NOTE);
        }

        if(note.getId() == null)
        {
            note.setId(findByBookAndList(note.getBook().getIsbn(), note.getList().getName()).getId());
        }
        noteRepository.save(note);
    }

    public Note findById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_NOTE));
    }

    public Note findByBookAndList(String isbn, String name) {
        return noteRepository.findByBookAndList(bookService.findById(isbn), listService.findByName(name));
    }

    public void add(Note note) {
        if(noteRepository.existsByBookAndList(note.getBook(), note.getList()))
        {
            throw new IllegalArgumentException(
                    String.format("Note already exists for book %s in list %s!",
                            note.getBook().getTitle(), note.getList().getName())
            );
        }

        noteRepository.save(note);
    }

    public void delete(Long id) {
        if(!noteRepository.existsById(id))
        {
            throw new NoSuchElementException(NOT_FOUND_NOTE);
        }
        noteRepository.deleteById(id);
    }

}
