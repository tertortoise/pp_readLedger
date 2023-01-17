package io.tertortoise.readledger_api.mappers;

import io.tertortoise.readledger_api.dtos.BookSlimDto;
import io.tertortoise.readledger_api.models.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    Book updateBookFromBookSlimDto(BookSlimDto bookSlimDto, @MappingTarget Book book);

}
