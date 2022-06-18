package com.example.book_library.model;

import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @Column(nullable = false)
    private String name;

    public Genre(String name) {
        setName(name);
    }

    public Genre() {
        setName("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null) ? "" : name;
    }
}
