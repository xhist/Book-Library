package com.example.book_library.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lists")
public class ListBooks {
    @Id
    private String name;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private Set<Note> notes;

    public ListBooks() {}

    public ListBooks(String name, Set<Note> notes) {
        this.name = name;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
