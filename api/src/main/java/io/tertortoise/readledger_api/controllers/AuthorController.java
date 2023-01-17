package io.tertortoise.readledger_api.controllers;

import java.util.List;
import java.util.UUID;

import io.tertortoise.readledger_api.dtos.AuthorSlimDto;
import jakarta.validation.Valid;

import io.tertortoise.readledger_api.models.Author;
import io.tertortoise.readledger_api.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/findAll")
    public List<Author> getAll() {

        return authorService.findAll();

    }

    @PostMapping("/add")
    public UUID addEntity(@Valid @RequestBody Author authorData) {

        // WIP author title

        return authorService.insert(authorData);

    }

    @PostMapping("/update")
    public ResponseEntity<UUID> updateEntity(@Valid @RequestBody AuthorSlimDto authorSlimDto) {

        UUID id =  authorService.update(authorSlimDto);

        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UUID> deleteById(@PathVariable UUID id) {

        authorService.deleteById(id);

        return new ResponseEntity<>(id, HttpStatus.OK);

    }


}
