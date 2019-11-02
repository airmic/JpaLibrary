package ru.otus.jpalibrary.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import ru.otus.jpalibrary.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;



@Repository
public class GenreRepositoryImpl implements GenreRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Genre> findById(int id) {
        return Optional.ofNullable(em.find(Genre.class,id));
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g",Genre.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Genre genre) {
        if( genre.getId() == 0 )
            em.persist(genre);
        else
            em.merge(genre);

    }


    @Override
    public Optional<Genre> findByName(String genreName) {
        try {
            return Optional.ofNullable( em.createNamedQuery("getGenresByName", Genre.class).setParameter("genrename", genreName).getSingleResult());
        } catch (EmptyResultDataAccessException | NoResultException e) {
            return Optional.empty();
        }
    }

}
