package ru.otus.jpalibrary.service;

import ru.otus.jpalibrary.domain.Author;
import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Comment;
import ru.otus.jpalibrary.domain.Genre;

import java.util.List;

public interface ConsoleService {
    void printHelp();
    void printGenreList(List<Genre> genres) ;
    void printGenre(Genre genre) ;
    void printAuthor(Author author) ;
    void printAuthorList(List<Author> authors) ;
    void printBookInfo(Book book) ;
    void printAllBooks(List<Book> books) ;
    void printCommentConfirm(Comment comment) ;
    void printBookComments(Book book, List<Comment> comments) ;
}
