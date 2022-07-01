package com.example.book_library.service;

import com.example.book_library.model.ListBooks;
import com.example.book_library.repository.IListBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ListBooksService {
    @Autowired
    private IListBooksRepository listRepository;

    private final String NOT_FOUND_LIST = "List not found!";

    public List<ListBooks> findAll() {
        LinkedList<ListBooks> lists = new LinkedList<>();
        listRepository.findAll().forEach(lists::add);
        return lists;
    }

    public ListBooks findByName(String name) {
        return listRepository.findById(name).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_LIST));
    }

    public void add(ListBooks list) {
        if(list.getName() == null || list.getName() == "")
        {
            throw new IllegalArgumentException("The name must not be empty or null!");
        }

        if(listRepository.existsById(list.getName()))
        {
            throw new IllegalArgumentException(
                    String.format("List %s already exists!", list.getName())
            );
        }

        listRepository.save(list);
    }

    public void delete(String name) {
        if(!listRepository.existsById(name)) {
            throw new NoSuchElementException(NOT_FOUND_LIST);
        }
        listRepository.deleteById(name);
    }
}
