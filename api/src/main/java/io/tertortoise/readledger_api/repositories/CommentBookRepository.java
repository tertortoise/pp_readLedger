package io.tertortoise.readledger_api.repositories;

import io.tertortoise.readledger_api.models.CommentBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentBookRepository extends JpaRepository<CommentBook, UUID> {

    List<CommentBook> findByBookIdIn(List<UUID> bookIds);

}
