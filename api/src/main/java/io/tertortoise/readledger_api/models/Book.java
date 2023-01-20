package io.tertortoise.readledger_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

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
    @Column(name="book_title", nullable = false, columnDefinition = "text")
    private String bookTitle;

    @Column(columnDefinition = "varchar(16) default 'ACQUIRE'")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    /**
     * wip
     * seriesId maybe null, books should be deleted upon deleting series?
     * ordinal - in series default 1?
     * */

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

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore(false)
    private List<CommentBook> comments = new ArrayList<CommentBook>();

    public void addComment(CommentBook comment) {

        comments.add(comment);
        comment.setBook(this);

    }


    public Book(String bookTitle) {

        this.bookTitle = bookTitle;

    }

}
