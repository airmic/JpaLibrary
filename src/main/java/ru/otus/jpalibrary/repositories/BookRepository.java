package ru.otus.jpalibrary.repositories;

import ru.otus.jpalibrary.domain.Author;
import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Comment;
import ru.otus.jpalibrary.domain.User;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> getAllBookSimple();
    List<Book> getAll();
    Optional<List<Comment>> getBookComents(Book book);
    Comment saveBookComment(String commentStr, Book book, User user, Comment upperComment);
    void save(Book book);
    Optional<Book> getBookById(long book_id);
    Optional<Book> getBookByNameAndYear(String bookName, Integer issueYear);
    Optional<List<Book>> getBookByAuthor(Author author);
}
