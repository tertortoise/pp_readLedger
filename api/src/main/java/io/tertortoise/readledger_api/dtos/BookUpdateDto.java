package io.tertortoise.readledger_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("authors")
    private Set<UUID> authorIds;

}
