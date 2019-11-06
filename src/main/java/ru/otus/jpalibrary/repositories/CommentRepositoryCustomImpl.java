package ru.otus.jpalibrary.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Comment;
import ru.otus.jpalibrary.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Comment saveBookComment(String commentStr, Book book, User user, Comment upperComment) {
        Comment comment1 = new Comment();
        comment1.setBook(book);
        comment1.setUser(user);
        comment1.setText(commentStr);
        if( upperComment != null)
            comment1.setUpId(upperComment.getUpId());
        em.persist(comment1);
        return comment1;
    }
}
