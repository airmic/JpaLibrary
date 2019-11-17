package ru.otus.jpalibrary.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.context.annotation.Import;
import ru.otus.jpalibrary.domain.User;
import ru.otus.jpalibrary.repositories.comm.ContactTypeEn;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@Import(UserRepositoryImpl.class)
class UserRepositoryImplTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void getUserByContact() {
        Optional<User> userOp = userRepository.getUserByContact("vasya@mail.ru", ContactTypeEn.EMAIL.value());
        assertTrue(userOp.isPresent());
        userOp.ifPresent(user ->
                assertAll(() -> assertEquals("vasya", user.getNick())
                        , () -> assertEquals(2, user.getContacts().size())
                        , () -> assertEquals(1, user.getComments().size())
                        , () -> assertEquals("Хорошая книга2", user.getComments().get(0).getText())
                        , () -> assertEquals( "Горе от ума", user.getComments().get(0).getBook().getBookName())
                )
        );
    }

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();
        assertAll(
                () -> assertEquals(2,users.size())
                ,() -> assertEquals(1,users.get(1).getComments().size())
        );
    }
}