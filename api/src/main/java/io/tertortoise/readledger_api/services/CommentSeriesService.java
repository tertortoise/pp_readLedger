package io.tertortoise.readledger_api.services;

import io.tertortoise.readledger_api.mappers.CommentSeriesMapper;
import io.tertortoise.readledger_api.models.CommentSeries;
import io.tertortoise.readledger_api.dtos.CommentSeriesNew;
import io.tertortoise.readledger_api.models.Series;
import io.tertortoise.readledger_api.repositories.CommentSeriesRepository;
import io.tertortoise.readledger_api.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentSeriesService {
    @Autowired
    private CommentSeriesRepository repository;

    @Autowired
    private SeriesRepository seriesRepository;

    public List<CommentSeries> findAll() {

        return repository.findAll();

    }

    public Optional<CommentSeries> findById(UUID id) {

        return repository.findById(id);

    }

    public List<CommentSeries> findAllBySeriesIds(List<UUID> seriesIds) {

        return repository.findBySeriesIdIn(seriesIds);

    }

    public UUID insert(CommentSeriesNew commentsSeriesData) {

        // this should belong to controller
        // service should be called only if series is found

        CommentSeries commentsSeries = new CommentSeries(commentsSeriesData.getCommentContent());

        UUID seriesId = commentsSeriesData.getSeriesId();

        Optional<Series> series = seriesRepository.findById(commentsSeriesData.getSeriesId());

        if (series.isPresent()) {

            series.get().addComment(commentsSeries);

            repository.save(commentsSeries);

            return commentsSeries.getId();

        }

        return null;

    }

    public void update(CommentSeries commentSeries) {

        repository.save(commentSeries);

    }

}
