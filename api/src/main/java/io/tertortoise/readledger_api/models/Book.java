package io.tertortoise.readledger_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="id", nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message="book title is mandatory")
    @Column(name="author_name", nullable = false, columnDefinition = "text")
    private String bookTitle;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_authors", joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author) {

        this.authors.add(author);
        author.getBooks().add(this);

    }

    public void removeAuthor(UUID authorId) {

        Author author =
                this.authors.stream().filter(a -> a.getId() == authorId).findFirst().orElse(null);

        if (author != null) {

            this.authors.remove(author);
            author.getBooks().remove(this);

        }

    }

    public Book(String bookTitle) {

        this.bookTitle = bookTitle;

    }

}
