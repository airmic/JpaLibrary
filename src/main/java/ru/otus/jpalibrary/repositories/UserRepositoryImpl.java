package ru.otus.jpalibrary.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import ru.otus.jpalibrary.domain.User;
import ru.otus.jpalibrary.repositories.comm.ContactTypeEn;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Optional<User> getUserByContact(String contactVal, ContactTypeEn contactTypeEn) {
        try {
            return Optional.ofNullable(em.createQuery("select u " +
                            "from User u join u.contacts c join c.type ct " +
                            "where c.value = :c_val and ct.name = :ct_val"
                    , User.class)
                    .setParameter("c_val", contactVal)
                    .setParameter("ct_val", contactTypeEn.value())
                    .getSingleResult());
        } catch (EmptyResultDataAccessException | NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
}
