package io.tertortoise.readledger_api.repositories;

import io.tertortoise.readledger_api.models.CommentSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentSeriesRepository extends JpaRepository<CommentSeries, UUID> {

    List<CommentSeries> findBySeriesIdIn(List<UUID> seriesIds);
}
