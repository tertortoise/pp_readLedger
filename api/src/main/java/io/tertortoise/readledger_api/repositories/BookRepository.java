package io.tertortoise.readledger_api.repositories;

import io.tertortoise.readledger_api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
