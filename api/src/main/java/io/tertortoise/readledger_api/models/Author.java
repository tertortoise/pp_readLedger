package io.tertortoise.readledger_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Author {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="id", nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message="author name is mandatory")
    @Column(name="author_name", nullable = false, columnDefinition = "text")
    private String authorName;

    @ManyToMany(fetch = FetchType.LAZY,  cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "authors")
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Author(String authorName) {

        this.authorName = authorName;

    }

}
