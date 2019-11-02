package ru.otus.jpalibrary.repositories;

import ru.otus.jpalibrary.domain.User;
import ru.otus.jpalibrary.repositories.comm.ContactTypeEn;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByContact(final String contactVal, final ContactTypeEn contactTypeEn);
    Optional<User> findById(long id);
    List<User> findAll();
}
