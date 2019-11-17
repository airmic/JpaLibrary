package ru.otus.jpalibrary.repositories;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.jpalibrary.domain.Author;
import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Genre;
import ru.otus.jpalibrary.domain.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@Import({BookRepositoryImpl.class, UserRepositoryImpl.class})
class BookRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    private SessionFactory sessionFactory;
//    @BeforeEach
    void setUp() {
        sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        sessionFactory.getStatistics().clear();
    }


    @DisplayName("getAll - fetch вариант")
    @Test
    void getAll() {
        setUp();
        List<Book> books = bookRepository.findAll();
        assertAll(
                () -> assertNotNull(books, "Не должен быть равен нулю")
                , () -> assertEquals(5,books.size())
                , () -> assertNotNull(books.get(0).getGenre())
                , () -> assertNotNull(books.get(0).getAuthors())
                , () -> assertEquals(1, books.get(0).getAuthors().size())
                , () -> assertEquals(1, sessionFactory.getStatistics().getPrepareStatementCount())

        );
    }


    @Test
    void addCommentToBook() {
        Optional<Book> book = bookRepository.getBookById(5);
        assertNotNull(book.isPresent());
        book.ifPresent( book1 -> {
            Optional<User> user = userRepository.findById(1);
            assertNotNull(user);
            final int[] commentCount = {0};
            commentRepository.getComents(book1).ifPresent(comments -> {
                commentCount[0] = comments.size();
            });
            user.ifPresent(user1 -> {
                commentRepository.saveBookComment("Очень хорошая книга",book1,user1, null);
            });
            commentRepository.getComents(book1).ifPresent(comments -> {
                assertEquals(commentCount[0]+1,comments.size());
            });

        });

    }


    @Test
    void save() {
        Book book = new Book();
        Author author = em.find(Author.class,1L);
        book.setAuthors(Collections.singletonList( author ));
        book.setBookName("GeneratioNN \"П\"");
        Genre genre = em.find(Genre.class,1);
        book.setGenre(genre);
        book = bookRepository.save(book);
        assertNotEquals(0, book.getId());
        Book book2 = em.find(Book.class, book.getId());
        assertAll(
                () -> assertEquals("GeneratioNN \"П\"",book2.getBookName())
                ,() -> assertEquals(1, book2.getAuthors().size())
                ,() -> assertEquals( em.find(Author.class, author.getId()), book2.getAuthors().get(0))
                ,() -> assertEquals( em.find(Genre.class, genre.getId()), book2.getGenre() )
        );
    }

    @Test
    void getBookById() {
        Optional<Book> obook = bookRepository.getBookById(1);
        assertTrue(obook.isPresent(),"Должна найтись книга");
        obook.ifPresent(book -> {
            assertAll(() -> assertEquals("Горе от ума", book.getBookName()));
        });
    }

    @Test
    void getBookByNameAndYear() {
        Optional<Book> obook =bookRepository.getBookByBookNameAndIssueYear("Горе от ума",1957);
        assertTrue(obook.isPresent(),"Должна найтись книга");
        obook.ifPresent(book -> {
            assertAll(() -> assertEquals(1, book.getId()));
        });
    }

    @Test
    void getBookByAuthor() {
        Author author = em.find(Author.class,1L);
        Optional<List<Book>> obookList = bookRepository.getBookByAuthor(author);
        assertAll(() -> assertTrue(obookList.isPresent(),"Список книг не дб НУЛом"));
        obookList.ifPresent( bookList -> {
            assertAll(() -> assertEquals(1L, bookList.size()));
        });

    }


}