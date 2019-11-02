package ru.otus.jpalibrary.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.jpalibrary.domain.Author;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository {
    Optional<Author> findById(long id);
    List<Author> findAll();
    void save(Author author);
    Optional<Author> findByFIOAndBD(String lastName, String firstName, String middleName, Date birthDate);
}
