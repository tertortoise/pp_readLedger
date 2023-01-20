package io.tertortoise.readledger_api.mappers;

import io.tertortoise.readledger_api.dtos.CommentBookUpdateDto;
import io.tertortoise.readledger_api.models.CommentBook;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CommentBookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    CommentBook updateCommentBookFromCommentBookUpdateDto(CommentBookUpdateDto commentBookUpdateDto, @MappingTarget CommentBook commentBook);
}
