package ru.otus.jpalibrary.domain;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(name = "books_unq", columnNames = {"book_name","issue_year"}))
public class Book {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;
    @Column(name = "book_name", nullable = false)
    private String bookName;
    @Column(name = "issue_year", nullable = false)
    private int issueYear;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Genre.class )
    @JoinColumn(nullable = false, unique = true, name = "genre_id", referencedColumnName = "genre_id")
    private Genre genre;
    @ManyToMany
    @JoinTable(uniqueConstraints =@UniqueConstraint(name = "book_author_unq", columnNames = {"book_id", "author_id"}) ,joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
}