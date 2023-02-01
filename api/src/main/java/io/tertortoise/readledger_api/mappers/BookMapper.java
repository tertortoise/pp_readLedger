package io.tertortoise.readledger_api.mappers;

import io.tertortoise.readledger_api.dtos.BookFullDto;
import io.tertortoise.readledger_api.dtos.BookSlimDto;
import io.tertortoise.readledger_api.models.Author;
import io.tertortoise.readledger_api.models.Book;
import io.tertortoise.readledger_api.models.Series;
import io.tertortoise.readledger_api.services.AuthorService;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    public Book updateBookFromBookSlimDto(BookSlimDto bookSlimDto, @MappingTarget Book book);

    @Mapping(source = "series", target = "seriesId", qualifiedByName = "seriesToSeriesId")
    public BookFullDto getBookFullDtoFromBook(Book book);

    @Named("seriesToSeriesId")
    public static UUID seriesToSeriesId(Series series) {

        if (series == null) {

            return null;

        }

        return series.getId();

    }

    public List<BookFullDto> getListBookFullDtoFromListBook(List<Book> books);

}
