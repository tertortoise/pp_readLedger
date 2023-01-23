package io.tertortoise.readledger_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.tertortoise.readledger_api.models.BookStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.UUID;

@Data
public class BookCreate {

    @JsonProperty("title")
    private String bookTitle;

    private BookStatus status;

    @Size(min = 1)
    @JsonProperty("authors")
    private Set<UUID> authorIds;

    @JsonProperty("series")
    private UUID seriesId;

    private Integer ordinalInSeries;



    // seriesId: nullable
    // status: enum

}
