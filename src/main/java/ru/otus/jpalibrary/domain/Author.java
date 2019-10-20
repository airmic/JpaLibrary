package ru.otus.jpalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors"
        , uniqueConstraints = {
            @UniqueConstraint(name="author_fio_bd_unq", columnNames = {"last_name","first_name","father_name", "birth_date"}),})
public class Author {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;
    @Column(name = "last_name", length = 150, nullable = false)
    private String lastName;
    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;
    @Column(name = "father_name", length = 150)
    private String fatherName;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

}
