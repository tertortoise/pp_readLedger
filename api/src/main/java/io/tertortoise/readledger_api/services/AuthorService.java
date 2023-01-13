package io.tertortoise.readledger_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.tertortoise.readledger_api.dtos.AuthorSlimDto;
import io.tertortoise.readledger_api.mappers.AuthorMapper;
import io.tertortoise.readledger_api.models.Author;
import io.tertortoise.readledger_api.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<Author> findAll() {

        return (List<Author>) repository.findAll();

    }

    public UUID insert(Author authorData) {

        Author author = new Author(authorData.getAuthorName());

        repository.save(author);

        return author.getId();

    }

    public UUID update(AuthorSlimDto authorSlimDto) {

        UUID id = authorSlimDto.getId();

        Optional<Author> authorToUpdateOpt = repository.findById(id);

        if (authorToUpdateOpt.isPresent()) {

            repository.save(authorMapper.updateAuthorFromAuthorSlimDto(authorSlimDto,
                    authorToUpdateOpt.get()));

            return id;

        } else {

            return null;

        }

    }

    public UUID deleteById(UUID id) {

        repository.deleteById(id);

        return id;

    }
}
