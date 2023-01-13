package io.tertortoise.readledger_api.mappers;

import io.tertortoise.readledger_api.dtos.AuthorSlimDto;
import io.tertortoise.readledger_api.models.Author;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    Author updateAuthorFromAuthorSlimDto(
            AuthorSlimDto authorSlimDto, @MappingTarget Author author);

}
