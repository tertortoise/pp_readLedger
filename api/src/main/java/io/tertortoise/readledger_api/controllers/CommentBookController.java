package io.tertortoise.readledger_api.controllers;


import io.tertortoise.readledger_api.dtos.CommentBookCreate;
import io.tertortoise.readledger_api.dtos.CommentBookUpdateDto;
import io.tertortoise.readledger_api.dtos.CommentSeriesCreate;
import io.tertortoise.readledger_api.dtos.CommentSeriesUpdateDto;
import io.tertortoise.readledger_api.mappers.CommentBookMapper;
import io.tertortoise.readledger_api.models.CommentBook;
import io.tertortoise.readledger_api.models.CommentSeries;
import io.tertortoise.readledger_api.requests.RequestIds;
import io.tertortoise.readledger_api.services.CommentBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("comments-book")
public class CommentBookController {

    @Autowired
    private CommentBookService commentBookService;

    @Autowired
    private CommentBookMapper commentBookMapper;

    @PostMapping("/findAll")
    public List<CommentBook> getAllCommentBook() {

        return commentBookService.findAll();

    }

    @PostMapping("/findByBook")
    public List<CommentBook> getCommentsByBook(@RequestBody RequestIds bookIds) {

        return commentBookService.findAllByBookIds(bookIds.getIds());

    }

    @PostMapping("/add")
    public UUID addEntity(@Valid @RequestBody CommentBookCreate commentBookCreateData) {

        return commentBookService.insert(commentBookCreateData);

    }

    @PostMapping("/update")
    public ResponseEntity<UUID> updateEntity(@Valid @RequestBody
    CommentBookUpdateDto commentBookUpdateDto) {

        UUID id = commentBookUpdateDto.getId();

        Optional<CommentBook> commentBookToUpdateOpt = commentBookService.findById(id);

        if (commentBookToUpdateOpt.isPresent()) {

            CommentBook newCommentBook =
                    commentBookMapper.updateCommentBookFromCommentBookUpdateDto(commentBookUpdateDto, commentBookToUpdateOpt.get());

            commentBookService.update(newCommentBook);

            return new ResponseEntity<>(id, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UUID> deleteById(@PathVariable UUID id) {

        commentBookService.deleteById(id);

        return new ResponseEntity<>(id, HttpStatus.OK);

    }



}
