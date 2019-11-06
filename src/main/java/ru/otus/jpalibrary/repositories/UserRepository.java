package ru.otus.jpalibrary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.jpalibrary.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u " +
            "from User u join u.contacts c join c.type ct " +
            "where c.value = :cVal and ct.name = :ctVal")
    Optional<User> getUserByContact(final String cVal, final String ctVal);
    Optional<User> findById(long id);
    List<User> findAll();
}
