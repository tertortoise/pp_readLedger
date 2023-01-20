package io.tertortoise.readledger_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "comments_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentBook {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="id", nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message="comment content is mandatory")
    @Column(name="comment_content", nullable = false, columnDefinition = "text")
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_book")
    @JsonBackReference
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentBook)) return false;
        return id != null && id.equals(((CommentBook) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public CommentBook(String commentContent) {

        this.commentContent = commentContent;

    }




}
