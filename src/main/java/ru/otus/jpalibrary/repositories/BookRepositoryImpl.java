package ru.otus.jpalibrary.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import ru.otus.jpalibrary.domain.Author;
import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Comment;
import ru.otus.jpalibrary.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Book> getAllBookSimple() {
        return em.createQuery("select b from Book b join b.authors a join b.genre where a.firstName<>'Ivan' ",Book.class).getResultList();
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b join fetch b.authors a join fetch b.genre where a.firstName<>'Ivan' ",Book.class).getResultList();
    }

    @Override
    public Optional<List<Comment>> getBookComents(Book book) {
        return Optional.ofNullable(em.createQuery("select c from Comment c join c.book b where b.id=:id",Comment.class)
                .setParameter("id", book.getId()).getResultList());
    }

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

    @Override
    @Transactional
    public void save(Book book) {
        if( book.getId() ==0 ) {
            em.persist(book);
        } else
            em.merge(book);
    }

    @Override
    public Optional<Book> getBookById(long book_id) {
        try {
            return Optional.of(em.find(Book.class, book_id));
        } catch(EmptyResultDataAccessException | NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> getBookByNameAndYear(String bookName, Integer issueYear) {
        try {
            return Optional.of(em.createQuery("select b from Book b where b.bookName=:bookName and b.issueYear=:issYear", Book.class)
                    .setParameter("bookName", bookName)
                    .setParameter("issYear", issueYear)
                    .getSingleResult());
        } catch(EmptyResultDataAccessException | NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Book>> getBookByAuthor(Author author) {
        return Optional.of(em.createQuery("select b from Book b join b.authors a join b.genre where a = :author ", Book.class).setParameter("author", author).getResultList());
    }
}
