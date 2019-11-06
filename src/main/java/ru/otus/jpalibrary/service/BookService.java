package ru.otus.jpalibrary.service;

import ru.otus.jpalibrary.domain.*;

import java.sql.Date;
import java.util.List;

public interface BookService {
    Genre getFindedOrCreatedGenreByName(final String genre);
    Author getFindedOrCreatedAuthorsByName(final String lastName, final String firstName, final String middleName, final Date birthDate);
    List<Genre> getAllGenres();
    List<Author> getAllAuthors();
    Book createNewBookIfSameNotExist(final String bookName, final Integer issueYear, final long authorId, final int genreId);
    List<Book> getAllBooks();
    Comment addCommentToBook(long bookId, String comment, User user);
    Book getBookById(long bookId);
    List<Comment> getBookComments(Book book);
}
