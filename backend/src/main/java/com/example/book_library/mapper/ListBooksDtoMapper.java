package com.example.book_library.mapper;

import com.example.book_library.dto.ListBooksDto;
import com.example.book_library.dto.NoteDto;
import com.example.book_library.model.ListBooks;
import com.example.book_library.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ListBooksDtoMapper {
    private NoteDtoMapper noteMapper;

    @Autowired
    public ListBooksDtoMapper(NoteDtoMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public ListBooks convertToEntity(ListBooksDto listBooksDto) {
        ListBooks list = new ListBooks();
        list.setName(listBooksDto.getName());
        return list;
    }

    public ListBooksDto convertToDto(ListBooks list) {
        return new ListBooksDto(list.getName());
    }

    public List<ListBooksDto> convertListToDtos(List<ListBooks> lists) {
        return lists.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ListBooks> convertListToEntities(List<ListBooksDto> listsDtos) {
        return listsDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
