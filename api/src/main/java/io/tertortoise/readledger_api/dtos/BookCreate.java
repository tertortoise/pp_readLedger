package io.tertortoise.readledger_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.UUID;

@Data
public class BookCreate {

    @JsonProperty("title")
    private String bookTitle;

    @Size(min = 1)
    @JsonProperty("authors")
    private Set<UUID> authorIds;

    // seriesId: nullable
    // status: enum

}
