package ru.otus.jpalibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.jpalibrary.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre,Integer> {
    Optional<Genre> findById(int id);
    List<Genre> findAll();
    Genre save(Genre genre);
    Optional<Genre> findByGenreName(String genreName);
}
