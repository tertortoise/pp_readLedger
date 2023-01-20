package io.tertortoise.readledger_api.services;

import io.tertortoise.readledger_api.dtos.CommentBookCreate;
import io.tertortoise.readledger_api.mappers.CommentBookMapper;
import io.tertortoise.readledger_api.models.Book;
import io.tertortoise.readledger_api.models.CommentBook;
import io.tertortoise.readledger_api.models.CommentSeries;
import io.tertortoise.readledger_api.repositories.BookRepository;
import io.tertortoise.readledger_api.repositories.CommentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentBookService {

    @Autowired
    private CommentBookRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentBookMapper commentBookMapper;

    public List<CommentBook> findAll() {

        return repository.findAll();

    }

    public Optional<CommentBook> findById(UUID id) {

        return repository.findById(id);

    }

    public List<CommentBook> findAllByBookIds(List<UUID> bookIds) {

        return repository.findByBookIdIn(bookIds);

    }

    public UUID insert(CommentBookCreate commentBookCreateData) {

        UUID bookId = commentBookCreateData.getBookId();

        Optional<Book> book = bookRepository.findById(bookId);

        if (!book.isPresent()) {

            return null;

        }

        CommentBook commentBook = new CommentBook(commentBookCreateData.getCommentContent());

        book.get().addComment(commentBook);

        repository.save(commentBook);

        return commentBook.getId();
    }

    public void update(CommentBook commentBook) {

        repository.save(commentBook);

    }

    public UUID deleteById(UUID id) {

        repository.deleteById(id);

        return id;

    }



}
