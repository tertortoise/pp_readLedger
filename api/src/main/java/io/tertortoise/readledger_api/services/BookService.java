package io.tertortoise.readledger_api.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import io.tertortoise.readledger_api.dtos.BookCreate;
import io.tertortoise.readledger_api.dtos.BookSlimDto;
import io.tertortoise.readledger_api.mappers.BookMapper;
import io.tertortoise.readledger_api.models.Author;
import io.tertortoise.readledger_api.models.Book;
import io.tertortoise.readledger_api.repositories.AuthorRepository;
import io.tertortoise.readledger_api.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<Book> findAll() {

        return repository.findAll();

    }

    public UUID insert(BookCreate bookData) {

        /**
         * wip
         * check that authorIds exist
         * existsAllByIdIn should do the trick
         * if exist - add, if not - do not add
         * */

        Set<UUID> authorIds = bookData.getAuthorIds();

        Boolean authorsExist = authorRepository.existsAllById(authorIds);

        if (!authorsExist) {

            return null;

        }

        Set<Author> authors = authorRepository.findByIdIn(authorIds);

        Book book = new Book(bookData.getBookTitle());

        book.setAuthors(authors); // wip should each athor be added via addAuthor or is it enough?

        repository.save(book);

        return book.getId();

    }

    public UUID update(BookSlimDto bookSlimDto) {

        UUID id = bookSlimDto.getId();

        Optional<Book> bookToUpdateOpt = repository.findById(id);

        if (bookToUpdateOpt.isPresent()) {

            repository.save(bookMapper.updateBookFromBookSlimDto(bookSlimDto,
                    bookToUpdateOpt.get()));

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
