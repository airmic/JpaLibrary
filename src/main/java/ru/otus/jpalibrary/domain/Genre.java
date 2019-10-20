package ru.otus.jpalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genres", uniqueConstraints = @UniqueConstraint(name = "gentes_name_unq", columnNames = {"genre_name"}))
public class Genre {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int id;
    @Column(name = "genre_name", length = 100, unique = true )
    private String genreName;

}
