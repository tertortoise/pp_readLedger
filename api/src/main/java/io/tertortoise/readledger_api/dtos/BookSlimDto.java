package io.tertortoise.readledger_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class BookSlimDto {

    @NotNull
    private UUID id;

    @NotNull
    @JsonProperty("title")
    private String bookTitle;

}
