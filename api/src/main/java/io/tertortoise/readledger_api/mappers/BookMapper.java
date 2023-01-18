package io.tertortoise.readledger_api.mappers;

import io.tertortoise.readledger_api.dtos.BookSlimDto;
import io.tertortoise.readledger_api.models.Author;
import io.tertortoise.readledger_api.models.Book;
import io.tertortoise.readledger_api.services.AuthorService;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    Book updateBookFromBookSlimDto(BookSlimDto bookSlimDto, @MappingTarget Book book);


}
