package io.tertortoise.readledger_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

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

    public Series(String seriesTitle) {

        this.seriesTitle = seriesTitle;

    }
}
