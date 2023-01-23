package io.tertortoise.readledger_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.tertortoise.readledger_api.models.BookStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;
import java.util.UUID;
@Data
public class BookUpdateDto {

    @NotNull
    private UUID id;

    @JsonProperty("title")
    private String bookTitle;

    private BookStatus status;

    @JsonProperty("authors")
    private Set<UUID> authorIds;

    private Boolean setToStandalone;

    @JsonProperty("series")
    private UUID seriesId;

    private Integer ordinalInSeries;

}
