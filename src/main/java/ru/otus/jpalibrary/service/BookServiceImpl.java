package ru.otus.jpalibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.jpalibrary.domain.*;
import ru.otus.jpalibrary.repositories.*;

import java.sql.Date;
import java.util.*;
import java.util.function.Supplier;

@Service
public class BookServiceImpl implements BookService {

    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private CommentRepository commentRepository;

    @Autowired
    public BookServiceImpl(GenreRepository genreRepository
                       , AuthorRepository authorRepository
                       , BookRepository bookRepository
                        , CommentRepository commentRepository
    ) {
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Genre getFindedOrCreatedGenreByName(final String genre) {
        Optional<Genre> ret = genreRepository.findByGenreName(genre);
        if( !ret.isPresent() ) {
            Genre newGenre = new Genre(genre);
            newGenre = genreRepository.save(newGenre);
            ret = Optional.of(newGenre);
        }
        return ret.get();
    }

    @Override
    public Author getFindedOrCreatedAuthorsByName(final String lastName, final String firstName, final String middleName, final Date birthDate) {
        Optional<Author> authorOptional = authorRepository.findByLastNameAndFirstNameAndMiddleNameAndBirthDate(lastName,firstName,middleName,birthDate);
        if( authorOptional.isPresent() )
            return authorOptional.get();
        Author author = new Author(lastName,firstName,middleName,birthDate);
        author = authorRepository.save(author);
        return author;
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Book createNewBookIfSameNotExist(final String bookName, final Integer issueYear, final long authorId, final int genreId) {

        Supplier<? extends Book> createNewBookWhenSearchFailed = () -> {
            Book bookt = new Book();
            bookt.setBookName(bookName);
            bookt.setIssueYear(issueYear);
            bookt.setGenre(
                    genreRepository.findById(genreId).orElseThrow( (Supplier<? extends RuntimeException>)() -> {
                        throw new AssertionError("ВВеден неверный ИД жанра");
                    })
            );
            bookt.setAuthors(
                    Collections.singletonList(
                            authorRepository.findById(authorId).orElseThrow( (Supplier<? extends RuntimeException>)() -> {
                                throw new RuntimeException("ВВеден неверный ИД актора");
                            })
                    ));
            bookt = bookRepository.save(bookt);
            return bookt;

        };
        return bookRepository.getBookByBookNameAndIssueYear(bookName, issueYear).orElseGet(createNewBookWhenSearchFailed);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Comment addCommentToBook(long bookId, String comment, User user) {
        Book book = bookRepository.getBookById(bookId).orElseThrow( (Supplier<? extends RuntimeException>)() -> {
            throw new RuntimeException("Введен неверный ИД книги");
        });
        return commentRepository.saveBookComment(comment,book,user,null);
    }

    @Override
    public Book getBookById(long bookId)  {
        Optional<Book> booko = bookRepository.getBookById(bookId);
        Supplier<? extends RuntimeException> supplier = () -> {throw new RuntimeException("Введен неверный ИД книги");};
        return bookRepository.getBookById(bookId).orElseThrow(supplier);
    }

    @Override
    public List<Comment> getBookComments(Book book) {
        return commentRepository.getComents(book).orElseThrow( (Supplier<? extends RuntimeException>) () -> {
            throw new RuntimeException("Не могу получить список комментариев");
        });
    }
}
