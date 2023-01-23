package io.tertortoise.readledger_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "series")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Series {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="id", nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message="series title is mandatory")
    @Column(name="series_title", nullable = false, columnDefinition = "text")
    private String seriesTitle;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore(false)
    private List<CommentSeries> comments = new ArrayList<CommentSeries>();

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore(false)
    private List<Book> books = new ArrayList<Book>();

    public void addComment(CommentSeries comment) {

        this.comments.add(comment);
        comment.setSeries(this);

    }

    public void addBook(Book book) {

        this.books.add(book);
        book.setSeries(this);

    }

    public Series(String seriesTitle) {

        this.seriesTitle = seriesTitle;

    }
}
