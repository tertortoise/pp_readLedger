package io.tertortoise.readledger_api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentSeriesNew {

    private String commentContent;

    private UUID seriesId;

}
