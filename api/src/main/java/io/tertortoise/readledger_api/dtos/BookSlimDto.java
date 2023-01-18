package io.tertortoise.readledger_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.tertortoise.readledger_api.models.Author;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class BookSlimDto {

    @NotNull
    private UUID id;

    private String bookTitle;

    private Set<Author> authors;

}
