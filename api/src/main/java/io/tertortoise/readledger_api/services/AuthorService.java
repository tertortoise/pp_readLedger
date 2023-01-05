package io.tertortoise.readledger_api.services;

import java.util.List;
import java.util.UUID;

import io.tertortoise.readledger_api.models.Author;
import io.tertortoise.readledger_api.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    public List<Author> findAll() {

        return (List<Author>) repository.findAll();

    }

    public UUID insert(Author authorData) {

        Author author = new Author(authorData.getAuthorName());

        repository.save(author);

        return author.getId();

    }
}
