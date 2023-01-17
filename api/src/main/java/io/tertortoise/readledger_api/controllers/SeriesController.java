package io.tertortoise.readledger_api.controllers;

import io.tertortoise.readledger_api.dtos.SeriesSlimDto;
import io.tertortoise.readledger_api.mappers.SeriesMapper;
import io.tertortoise.readledger_api.models.Series;
import io.tertortoise.readledger_api.requests.RequestSeriesGetCfg;
import io.tertortoise.readledger_api.services.SeriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private SeriesMapper seriesMapper;

    @PostMapping("/findAll")
    public List<?> getAll(@Valid @RequestBody RequestSeriesGetCfg requestSeriesGetCfg) {

        List<Series> seriesList = seriesService.findAll();

        if (requestSeriesGetCfg.getIncludeComments()) {

            return seriesList;

        } else {

            return seriesMapper.convertListSeriesToListSeriesSlimDto(seriesList);

        }

    }

    @PostMapping("/add")
    public UUID addEntity(@Valid @RequestBody Series seriesData) {

        Series series = new Series(seriesData.getSeriesTitle());

        return seriesService.insert(seriesData);

    }

    @PostMapping("/update")
    public ResponseEntity<UUID> updateEntity(@Valid @RequestBody SeriesSlimDto seriesSlimDto) {

        UUID id =  seriesService.update(seriesSlimDto);

        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UUID> deleteById(@PathVariable UUID id) {

        seriesService.deleteById(id);

        return new ResponseEntity<>(id, HttpStatus.OK);

    }

}
