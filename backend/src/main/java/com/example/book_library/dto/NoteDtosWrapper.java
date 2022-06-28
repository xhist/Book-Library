package com.example.book_library.dto;

import javax.validation.constraints.NotNull;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class NoteDtosWrapper {
    @NotNull
    private List<NoteDto> noteDtos;

    public NoteDtosWrapper() {
        this.noteDtos = new ArrayList<>();
    }

    public NoteDtosWrapper(List<NoteDto> noteDtos) {
        this.noteDtos = noteDtos;
    }

    public List<NoteDto> getNoteDtos() {
        return noteDtos;
    }

    public void setNoteDtos(List<NoteDto> noteDtos) {
        this.noteDtos = new ArrayList<>();
        for(NoteDto note : noteDtos)
            this.noteDtos.add(note);
    }
}
