package ru.otus.jpalibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.jpalibrary.domain.Genre;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест репозитария Жанров")
@DataJpaTest
class GenreRepositoryImplTest {

    private static final String FANTASTICA_ABR = "Afynfcnbrf";
    private static final String FANTASTICA = "Фантастика";
    @Autowired
    private TestEntityManager em;
    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("Поиск жанра по ИД")
    @Test
    void findById() {
        Optional<Genre> genre = genreRepository.findById(1);
        assertTrue(genre.isPresent(), "Должен присудствовать");
        genre.ifPresent( genre1 ->
            assertAll("Проверка поиска по ИД"
                    , () -> assertEquals(genre.get().getGenreName(),"Драма")
            )
        );
    }

    @DisplayName("Поиск всех жанров")
    @Test
    void findAll() {
        assertAll( "проверка поиска всех жанров"
                , () -> assertEquals(3, genreRepository.findAll().size())
        );
    }

    @DisplayName("Сохранение жанра")
    @Test
    void save() {
        Genre genre = new Genre();
        genre.setGenreName(FANTASTICA_ABR);
        genre = genreRepository.save(genre);

        assertEquals(4, genreRepository.findAll().size(), "Должно быть 4");
        assertNotEquals(genre.getId(), 0, "Идентификатор не должен быть равен 0");

        Genre genreFU = em.find(Genre.class,genre.getId());
        genreFU.setGenreName(FANTASTICA);
        genreFU = genreRepository.save(genreFU);

        Genre genreChk = em.find(Genre.class, genre.getId());
        assertEquals(FANTASTICA, genreChk.getGenreName(),"Названия жанров не совпадают");

    }

    @Test
    void findByName() {
        Optional<Genre> genre = genreRepository.findByGenreName("Драма");
        assertTrue(genre.isPresent(), "Должен присудствовать");
        genre.ifPresent( genre1 ->
            assertAll("Проверка поиска по названию жанра"
                    , () -> assertEquals(genre.get().getGenreName(),"Драма")
            )
        );
    }
}