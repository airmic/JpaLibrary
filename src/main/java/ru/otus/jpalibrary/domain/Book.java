package ru.otus.jpalibrary.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books",
        indexes = { @Index(name = "books_idx", columnList = "book_name,issue_year")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "issue_year", nullable = false)
    private int issueYear;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, targetEntity = Genre.class )
    @JoinColumn(nullable = false, name = "genre_id", referencedColumnName = "genre_id")
    private Genre genre;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(uniqueConstraints =@UniqueConstraint(name = "book_author_unq", columnNames = {"book_id", "author_id"}) ,joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

}
