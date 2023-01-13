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

/**
 * wip
 * bidirectional many-to-ony relationship with series
 *
 * */
@Entity
@Table(name = "comments_series")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSeries {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="id", nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message="series title is mandatory")
    @Column(name="comment_content", nullable = false, columnDefinition = "text")
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_series")
    @JsonBackReference
    private Series series;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentSeries)) return false;
        return id != null && id.equals(((CommentSeries) o).getId());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public CommentSeries(String commentContent) {

        this.commentContent = commentContent;

    }

}
