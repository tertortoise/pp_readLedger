package io.tertortoise.readledger_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    public void addComment(CommentSeries comment) {

        comments.add(comment);

        comment.setSeries(this);

    }

    public Series(String seriesTitle) {

        this.seriesTitle = seriesTitle;

    }
}
