package io.tertortoise.readledger_api.dtos;

import io.tertortoise.readledger_api.models.BookStatus;
import io.tertortoise.readledger_api.models.CommentBook;
import lombok.*;

import java.util.*;

@Data
public class BookFullDto {

    private UUID id;

    private String bookTitle;

    private BookStatus status;

    private Integer ordinalInSeries;

    private UUID seriesId;

    private Set<UUID> authorIds = new HashSet<>();

    private List<CommentBook> comments = new ArrayList<CommentBook>();

}
