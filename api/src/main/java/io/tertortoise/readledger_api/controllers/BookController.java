package io.tertortoise.readledger_api.controllers;

import java.util.List;
import java.util.UUID;

import io.tertortoise.readledger_api.dtos.BookCreate;
import io.tertortoise.readledger_api.dtos.BookFullDto;
import io.tertortoise.readledger_api.dtos.BookSlimDto;
import io.tertortoise.readledger_api.dtos.BookUpdateDto;
import io.tertortoise.readledger_api.mappers.BookMapper;
import io.tertortoise.readledger_api.models.Book;
import io.tertortoise.readledger_api.services.BookService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @PostMapping("/findAll")
    public List<BookFullDto> getAll() {

        List<Book> books = bookService.findAll();

        return bookMapper.getListBookFullDtoFromListBook(books);

    }

    @PostMapping("/add")
    public ResponseEntity<UUID> addEntity(@Valid @RequestBody BookCreate bookData) {

        UUID id = bookService.insert(bookData);

        if (id != null) {

            return new ResponseEntity<>(id, HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

    }

    @PostMapping("/update")
    public ResponseEntity<UUID> updateEntity(@Valid @RequestBody BookUpdateDto bookUpdateDto) {

        UUID id =  bookService.update(bookUpdateDto);

        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UUID> deleteById(@PathVariable UUID id) {

        bookService.deleteById(id);

        return new ResponseEntity<>(id, HttpStatus.OK);

    }


}
