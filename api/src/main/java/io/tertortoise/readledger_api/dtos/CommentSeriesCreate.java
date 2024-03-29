package io.tertortoise.readledger_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentSeriesCreate {

    @NotNull
    @JsonProperty("comment")
    private String commentContent;

    @NotNull
    @JsonProperty("series")
    private UUID seriesId;

}
