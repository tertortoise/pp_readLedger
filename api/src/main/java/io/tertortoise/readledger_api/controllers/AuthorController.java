package io.tertortoise.readledger_api.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import io.tertortoise.readledger_api.models.Author;
import io.tertortoise.readledger_api.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/findAll")
    public List<Author> getAllAuthors() {

        return authorService.findAll();

    }

    @PostMapping("/add")
    public UUID addResource(@Valid @RequestBody Author authorData) {

        return authorService.insert(authorData);

    }


}
