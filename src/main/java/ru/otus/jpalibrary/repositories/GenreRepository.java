package ru.otus.jpalibrary.repositories;

import ru.otus.jpalibrary.domain.Genre;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(int id);
    List<Genre> findAll();
    void save(Genre genre);
    Optional<Genre> findByName(String genreName);

}
