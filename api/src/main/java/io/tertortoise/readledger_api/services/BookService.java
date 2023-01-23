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
import io.tertortoise.readledger_api.models.BookStatus;
import io.tertortoise.readledger_api.models.Series;
import io.tertortoise.readledger_api.repositories.AuthorRepository;
import io.tertortoise.readledger_api.repositories.BookRepository;
import io.tertortoise.readledger_api.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<Book> findAll() {

        return repository.findAll();

    }

    public UUID insert(BookCreate bookData) {

        Set<UUID> authorIds = bookData.getAuthorIds();

        Boolean authorsExist = authorRepository.existsAllByIdIn(authorIds);

        if (!authorsExist) {

            return null;

        }

        UUID seriesId = bookData.getSeriesId();

        Optional<Series> series = null;

        if (seriesId != null) {

           series = seriesRepository.findById(seriesId);

           if (series.isEmpty()) {

               return null;

           }

        }

        Set<Author> authors = authorRepository.findByIdIn(authorIds);

        Book book = new Book(bookData.getBookTitle());

        book.setAuthors(authors);

        BookStatus bookStatus = bookData.getStatus() == null ? BookStatus.ACQUIRE :
                bookData.getStatus();

        book.setSeries(series.get());

        book.setOrdinalInSeries(bookData.getOrdinalInSeries());

        book.setStatus(bookStatus);

        repository.save(book);

        return book.getId();

    }

    public UUID update(BookUpdateDto bookUpdateDto) {

        UUID bookId = bookUpdateDto.getId();

        Optional<Book> bookToUpdateOpt = repository.findById(bookId);

        if (bookToUpdateOpt.isEmpty()) {

            return null;

        }

        String newTitle = bookUpdateDto.getBookTitle();

        BookStatus newStatus = bookUpdateDto.getStatus();

        Set<UUID> authorIds = bookUpdateDto.getAuthorIds();

        Boolean authorsExist = authorIds != null && authorIds.size() > 0;

        if (authorsExist) {

            authorsExist = authorRepository.existsAllByIdIn(authorIds);

        }

        Boolean setToStandalone = bookUpdateDto.getSetToStandalone();

        UUID seriesId = bookUpdateDto.getSeriesId();

        Integer ordinalInSeries = bookUpdateDto.getOrdinalInSeries();

        if (newStatus == null && newTitle == null && !authorsExist && setToStandalone == null && seriesId == null && ordinalInSeries == null) {

            return null;

        }

        Optional<Series> series = Optional.empty();

        if (setToStandalone != null && seriesId != null) {

            series = seriesRepository.findById(seriesId);

            if (series.isEmpty()) {

                return null;

            }

        }


        BookSlimDto bookSlimDto = new BookSlimDto();

        bookSlimDto.setId(bookId);

        bookSlimDto.setStatus(newStatus);

        bookSlimDto.setBookTitle(newTitle);

        if (setToStandalone == null || setToStandalone == false) {

            if (series.isPresent()) {

                bookSlimDto.setSeries(series.get());

            }

            bookSlimDto.setOrdinalInSeries(ordinalInSeries);

        }

        if (authorsExist) {

            Set<Author> newAuthors = authorRepository.findByIdIn(authorIds);

            bookSlimDto.setAuthors(newAuthors);

        }

        Book newBook = bookMapper.updateBookFromBookSlimDto(bookSlimDto,
                bookToUpdateOpt.get());

        if (setToStandalone != null && setToStandalone == true) {

            newBook.setSeries(null);
            newBook.setOrdinalInSeries(null);

        }

        repository.save(newBook);

        return bookId;


    }

    public UUID deleteById(UUID id) {

        repository.deleteById(id);

        return id;

    }
}
