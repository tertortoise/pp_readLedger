package io.tertortoise.readledger_api.mappers;

import io.tertortoise.readledger_api.dtos.CommentSeriesUpdateDto;
import io.tertortoise.readledger_api.models.CommentSeries;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CommentSeriesMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "commentContent", target = "commentContent")
    CommentSeries updateCommentSeriesFromCommentSeriesUpdateDto(CommentSeriesUpdateDto commentSeriesUpdateDto, @MappingTarget CommentSeries commentSeries);

}
