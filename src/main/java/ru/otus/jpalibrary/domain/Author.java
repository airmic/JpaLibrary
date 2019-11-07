package ru.otus.jpalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors"
        , uniqueConstraints = {
            @UniqueConstraint(name="author_fio_bd_unq", columnNames = {"last_name","first_name","father_name", "birth_date"}),})
@NamedNativeQueries({
        @NamedNativeQuery(name = "findByFIOAndBD"
                , query = "select * from authors " +
                "where first_name = :firstname " +
                "and last_name = :lastname " +
                "and father_name = :middlename " +
                "and birth_date = :birthdt")
        , @NamedNativeQuery(name = "findById", query = "select * from authors where id=:id")
        , @NamedNativeQuery(name = "FindAll", query = "select * from authors")
})
public class Author {

    public Author(long id,String lastName, String firstName, String middleName, Date birthDate) {
        this(id,lastName,firstName,middleName,birthDate, new ArrayList<Book>());
    }
    public Author(String lastName, String firstName, String middleName, Date birthDate) {
        this(0,lastName,firstName,middleName,birthDate, new ArrayList<Book>());
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;
    @Column(name = "last_name", length = 150, nullable = false)
    private String lastName;
    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;
    @Column(name = "father_name", length = 150)
    private String middleName;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

}
