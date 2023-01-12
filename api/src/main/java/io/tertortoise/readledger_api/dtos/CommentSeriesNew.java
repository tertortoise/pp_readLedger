package io.tertortoise.readledger_api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * wip change to dto pattern?
 * */
@Getter
@Setter
public class CommentSeriesNew {

//    private UUID id;

    private String commentContent;

    private UUID seriesId;

}
