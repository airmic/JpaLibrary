package ru.otus.jpalibrary.repositories;

import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Comment;
import ru.otus.jpalibrary.domain.User;

public interface CommentRepositoryCustom {
    Comment saveBookComment(String commentStr, Book book, User user, Comment upperComment);
}
