package io.tertortoise.readledger_api.repositories;

import io.tertortoise.readledger_api.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findAuthorsByBooksId(UUID bookId);

    Boolean existsAllById(Set<UUID> ids);

    Set<Author> findByIdIn(Set<UUID> id);

}
