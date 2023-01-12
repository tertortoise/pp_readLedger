package io.tertortoise.readledger_api.controllers;

import io.tertortoise.readledger_api.dtos.CommentSeriesUpdateDto;
import io.tertortoise.readledger_api.mappers.CommentSeriesMapper;
import io.tertortoise.readledger_api.models.CommentSeries;
import io.tertortoise.readledger_api.dtos.CommentSeriesNew;
import io.tertortoise.readledger_api.requests.RequestIds;
import io.tertortoise.readledger_api.services.CommentSeriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("comments-series")
public class CommentSeriesController {

    @Autowired
    private CommentSeriesService commentsSeriesService;

    @Autowired
    private CommentSeriesMapper commentSeriesMapper;

    @PostMapping("/findAll")
    public List<CommentSeries> getAllCommentSeries() {

        return commentsSeriesService.findAll();

    }

    @PostMapping("/findBySeries")
    public List<CommentSeries> getCommentBySeries(@RequestBody RequestIds seriesIds) {

        return commentsSeriesService.findAllBySeriesIds(seriesIds.getIds());

    }

    @PostMapping("/add")
    public UUID addCommentSeries(@Valid @RequestBody CommentSeriesNew commentSeriesData) {

        return commentsSeriesService.insert(commentSeriesData);

    }

    @PostMapping("/update")
    public ResponseEntity<UUID> updateCommentSeries(@Valid @RequestBody CommentSeriesUpdateDto commentSeriesUpdateDto) {

        UUID id = commentSeriesUpdateDto.getId();

        Optional<CommentSeries> commentSeriesToUpdateOpt = commentsSeriesService.findById(id);

        if (commentSeriesToUpdateOpt.isPresent()) {

            CommentSeries newCommentSeries = commentSeriesMapper.updateCommentSeriesFromCommentSeriesUpdateDto(commentSeriesUpdateDto, commentSeriesToUpdateOpt.get());

            commentsSeriesService.update(newCommentSeries);

            return new ResponseEntity<>(id, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }


}
