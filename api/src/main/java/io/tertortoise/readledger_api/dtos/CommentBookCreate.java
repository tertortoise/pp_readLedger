package io.tertortoise.readledger_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentBookCreate {

    @NotNull
    @JsonProperty("comment")
    private String commentContent;

    @NotNull
    @JsonProperty("book")
    private UUID bookId;


}
