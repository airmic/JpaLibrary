package ru.otus.jpalibrary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment,Long>, CommentRepositoryCustom{
    @Query("select c from Comment c join c.book b where b=:book")
    Optional<List<Comment>> getComents(Book book);
}
