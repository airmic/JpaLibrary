package ru.otus.jpalibrary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.jpalibrary.domain.Author;
import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Comment;
import ru.otus.jpalibrary.domain.User;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query("select b from Book b join fetch b.authors a join fetch b.genre")
    List<Book> findAll();
    Book save(Book book);
    Optional<Book> getBookById(long book_id);
    Optional<Book> getBookByBookNameAndIssueYear(String bookName, Integer issueYear);
    @Query("select b from Book b join b.authors a join b.genre where a = :author")
    Optional<List<Book>> getBookByAuthor(Author author);
}
