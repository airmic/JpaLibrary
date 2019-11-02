package ru.otus.jpalibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.jpalibrary.domain.*;
import ru.otus.jpalibrary.repositories.AuthorRepository;
import ru.otus.jpalibrary.repositories.BookRepository;
import ru.otus.jpalibrary.repositories.GenreRepository;

import java.sql.Date;
import java.util.*;
import java.util.function.Supplier;

@Service
public class BookService {

    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public BookService(GenreRepository genreRepository
                       , AuthorRepository authorRepository
                       , BookRepository bookRepository
    ) {
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Genre getFindedOrCreatedGenreByName(final String genre) {
        Optional<Genre> ret = genreRepository.findByName(genre);
        if( !ret.isPresent() ) {
            Genre newGenre = new Genre(genre);
            genreRepository.save(newGenre);
            ret = Optional.of(newGenre);
        }
        return ret.get();
    }

    public Author getFindedOrCreatedAuthorsByName(final String lastName, final String firstName, final String middleName, final Date birthDate) {
        Optional<Author> authorOptional = authorRepository.findByFIOAndBD(lastName,firstName,middleName,birthDate);
        if( authorOptional.isPresent() )
            return authorOptional.get();
        Author author = new Author(lastName,firstName,middleName,birthDate);
        authorRepository.save(author);
        return author;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Book createNewBookIsSameNotExist(final String bookName, final Integer issueYear, final long authorId, final int genreId) {

        return bookRepository.getBookByNameAndYear(bookName, issueYear).orElseGet(() -> {
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
            bookRepository.save(bookt);
            return bookt;

        });

    }


    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    public Comment addCommentToBook(long bookId, String comment, User user) {
        Book book = bookRepository.getBookById(bookId).orElseThrow( (Supplier<? extends RuntimeException>)() -> {
            throw new RuntimeException("Введен неверный ИД книги");
        });

        return bookRepository.addCommentToBook(comment,book,user,null);

    }

    public Book getBookById(long bookId)  {
        Optional<Book> booko = bookRepository.getBookById(bookId);
        Supplier<? extends RuntimeException> supplier = () -> {throw new RuntimeException("Введен неверный ИД книги");};
        return bookRepository.getBookById(bookId).orElseThrow(supplier);
    }

    public List<Comment> getBookComments(Book book) {

        return bookRepository.getBookComents(book).orElseThrow( (Supplier<? extends RuntimeException>) () -> {
            throw new RuntimeException("Не могу получить список комментариев");
        });
    }
}
