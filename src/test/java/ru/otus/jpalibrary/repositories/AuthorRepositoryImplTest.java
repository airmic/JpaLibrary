package ru.otus.jpalibrary.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.jpalibrary.domain.Author;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AuthorRepositoryImplTest {

    private static final String MIDDLE_NAME = "Осипович";
    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findById() {
        Optional<Author> author = authorRepository.findById(1);
        assertTrue(author.isPresent(),"Вернулся ТНУЛЛ");
        author.ifPresent(author1 -> {
            assertAll("Поиск автора по ИД"
                    , () -> assertEquals(1, author1.getId(), "id must be 1")
                    , () -> assertEquals( "Грибоедов", author1.getLastName(),"Должен быть Грибоедов")

            );
        });
    }

    @Test
    void findAll() {
        assertAll( "проверка поиска всех жанров"
                , () -> assertEquals(5, authorRepository.findAll().size())
        );
    }

    @Test
    void save() {
        Author author = new Author();
        author.setLastName("Богомолов");
        author.setFirstName("Владимир");
        author.setMiddleName("ОсиYович");
        author.setBirthDate(Date.valueOf("1926-07-03"));
        author = authorRepository.save(author);

        assertEquals(6, authorRepository.findAll().size(), "Должно быть 6");
        assertNotEquals(author.getId(), 0, "Идентификатор не должен быть равен 0");

        author = em.find(Author.class,author.getId());
        author.setMiddleName(MIDDLE_NAME);
        author = authorRepository.save(author);
        assertEquals(MIDDLE_NAME, author.getMiddleName(),"Отчества не совпадают");

        Author authorChk = em.find(Author.class, author.getId());
        assertEquals(MIDDLE_NAME, authorChk.getMiddleName(),"Отчества не совпадают");
    }

    @Test
    void findByFIOAndBD() {
        Optional<Author> author = authorRepository.findByLastNameAndFirstNameAndMiddleNameAndBirthDate("Грибоедов", "Александр", "Сергеевич", Date.valueOf("1795-01-15"));
        assertTrue(author.isPresent(),"Грибоедов не найден");
        author.ifPresent( (author1) -> assertAll( "Проверка результатов findByFIOAndBD"
                , () -> assertEquals( "Грибоедов", author1.getLastName(), "Отчетства не сопадают" )

        ));
    }
}