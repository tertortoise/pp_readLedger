package io.tertortoise.readledger_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentBookUpdateDto {

    @NotNull(message = "id should be provided for this request")
    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "updated comment content can not be empty")
    @JsonProperty("content")
    private String commentContent;

}
