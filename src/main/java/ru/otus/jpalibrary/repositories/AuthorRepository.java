package ru.otus.jpalibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.jpalibrary.domain.Author;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findById(long id);
    List<Author> findAll();
    Author save(Author author);
    Optional<Author> findByLastNameAndFirstNameAndMiddleNameAndBirthDate(String lastName, String firstName, String middleName, Date birthDate);
}
