package com.example.book_library.controller;

import com.example.book_library.dto.ListBooksDto;
import com.example.book_library.mapper.ListBooksDtoMapper;
import com.example.book_library.model.ListBooks;
import com.example.book_library.service.ListBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListBooksController {
    private ListBooksService listService;
    private ListBooksDtoMapper mapper;

    @Autowired
    public ListBooksController(ListBooksService listService, ListBooksDtoMapper mapper) {
        this.listService = listService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ListBooksDto> findAll() {
        return this.mapper.convertListToDtos(this.listService.findAll());
    }

    @GetMapping("/name/{name}")
    public ListBooksDto findByName(@PathVariable String name) {
        return this.mapper.convertToDto(this.listService.findByName(name));
    }

    @PostMapping
    public void add(@Valid @RequestBody ListBooksDto list){
        this.listService.add(this.mapper.convertToEntity(list));
    }

    @PutMapping
    public void update(@Valid @RequestBody ListBooksDto list) {
        this.listService.update(this.mapper.convertToEntity(list));
    }

    @DeleteMapping("/name/{name}")
    public void delete(@PathVariable String name) {
        this.listService.delete(name);
    }
}
