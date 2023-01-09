package io.tertortoise.readledger_api.controllers;

import io.tertortoise.readledger_api.models.Series;
import io.tertortoise.readledger_api.services.SeriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @PostMapping("/findAll")
    public List<Series> getAllSeries() {

        return seriesService.findAll();

    }

    @PostMapping("/add")
    public UUID addSeries(@Valid @RequestBody Series seriesData) {

        Series series = new Series(seriesData.getSeriesTitle());

        return seriesService.insert(seriesData);

    }

}
