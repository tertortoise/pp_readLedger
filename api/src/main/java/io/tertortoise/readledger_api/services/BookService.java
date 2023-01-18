package io.tertortoise.readledger_api.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import io.tertortoise.readledger_api.dtos.BookCreate;
import io.tertortoise.readledger_api.dtos.BookSlimDto;
import io.tertortoise.readledger_api.dtos.BookUpdateDto;
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

        Boolean authorsExist = authorRepository.existsAllByIdIn(authorIds);

        if (!authorsExist) {

            return null;

        }

        Set<Author> authors = authorRepository.findByIdIn(authorIds);

        Book book = new Book(bookData.getBookTitle());

        book.setAuthors(authors);

        repository.save(book);

        return book.getId();

    }

    public UUID update(BookUpdateDto bookUpdateDto) {

        System.out.println(bookUpdateDto);

        UUID bookId = bookUpdateDto.getId();

        Optional<Book> bookToUpdateOpt = repository.findById(bookId);

        if (bookToUpdateOpt.isEmpty()) {

            return null;

        }

        String newTitle = bookUpdateDto.getBookTitle();

        Set<UUID> authorIds = bookUpdateDto.getAuthorIds();

        Boolean authorsExist = authorIds != null && authorIds.size() > 0;

        if (authorsExist) {

            authorsExist = authorRepository.existsAllByIdIn(authorIds);

        }

        if (newTitle == null && !authorsExist) {

            return null;

        }

        BookSlimDto bookSlimDto = new BookSlimDto();

        bookSlimDto.setId(bookId);

        if (newTitle != null) {

            bookSlimDto.setBookTitle(newTitle);

        }

        if (authorsExist) {

            Set<Author> newAuthors = authorRepository.findByIdIn(authorIds);

            bookSlimDto.setAuthors(newAuthors);

        }


        repository.save(bookMapper.updateBookFromBookSlimDto(bookSlimDto,
                bookToUpdateOpt.get()));

        return bookId;


    }

    public UUID deleteById(UUID id) {

        repository.deleteById(id);

        return id;

    }
}
