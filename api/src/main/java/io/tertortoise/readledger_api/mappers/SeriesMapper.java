package io.tertortoise.readledger_api.mappers;

import io.tertortoise.readledger_api.dtos.SeriesSlimDto;
import io.tertortoise.readledger_api.models.Series;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeriesMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    Series updateSeriesFromSeriesSlimDto(SeriesSlimDto seriesSlimDto, @MappingTarget Series series);

    SeriesSlimDto convertSeriesToSeriesSlimDto(
            Series series);

    List<SeriesSlimDto> convertListSeriesToListSeriesSlimDto(List<Series> seriesList);

}
