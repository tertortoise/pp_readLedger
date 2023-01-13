package io.tertortoise.readledger_api.services;

import io.tertortoise.readledger_api.dtos.SeriesSlimDto;
import io.tertortoise.readledger_api.mappers.SeriesMapper;
import io.tertortoise.readledger_api.models.Series;
import io.tertortoise.readledger_api.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeriesMapper seriesMapper;

    public List<Series> findAll() {

        return seriesRepository.findAll();

    }

    public Optional<Series> findById(UUID id) {

        return seriesRepository.findById(id);

    }

    public UUID insert(Series seriesData) {

        Series series = new Series(seriesData.getSeriesTitle());

        seriesRepository.save(series);

        return series.getId();

    }

    public UUID update(SeriesSlimDto seriesSlimDto) {

        UUID id = seriesSlimDto.getId();

        Optional<Series> seriesToUpdate = seriesRepository.findById(id);

        if (seriesToUpdate.isPresent()) {

            seriesRepository.save(seriesMapper.updateSeriesFromSeriesSlimDto(seriesSlimDto,
                    seriesToUpdate.get()));

            return id;

        } else {

            return null;

        }

    }
    public UUID deleteById(UUID seriesId) {

        seriesRepository.deleteById(seriesId);

        return seriesId;

    }
}
