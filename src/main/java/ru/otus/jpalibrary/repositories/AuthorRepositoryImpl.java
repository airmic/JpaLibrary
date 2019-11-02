package ru.otus.jpalibrary.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import ru.otus.jpalibrary.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        return em.createNativeQuery("select * from authors", Author.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Author author) {
        if( author.getId() == 0 )
            em.persist(author);
        else
            em.merge(author);
    }

    @Override
    public Optional<Author> findByFIOAndBD(String lastName, String firstName, String middleName, Date birthDate) {
        try {
//            TODO узнать почему нужно явное преобразование, результирующий класс же указан??
            Author author = (Author) em.createNativeQuery("select * from authors " +
                    "where first_name = :firstname " +
                    "and last_name = :lastname " +
                    "and father_name = :middlename " +
                    "and birth_date = :birthdt", Author.class)
                    .setParameter("firstname",firstName)
                    .setParameter( "lastname", lastName)
                    .setParameter( "middlename", middleName)
                    .setParameter( "birthdt", birthDate)
                    .getSingleResult();
            return Optional.ofNullable( author);

        } catch (EmptyResultDataAccessException | NoResultException e) {
            return Optional.empty();
        }
    }
}
