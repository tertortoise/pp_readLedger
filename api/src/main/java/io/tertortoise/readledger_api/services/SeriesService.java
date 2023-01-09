package io.tertortoise.readledger_api.services;

import io.tertortoise.readledger_api.models.Series;
import io.tertortoise.readledger_api.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    public List<Series> findAll() {

        return seriesRepository.findAll();

    }

    public UUID insert(Series seriesData) {

        Series series = new Series(seriesData.getSeriesTitle());

        seriesRepository.save(series);

        return series.getId();

    }
}
