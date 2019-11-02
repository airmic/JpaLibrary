package ru.otus.jpalibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.jpalibrary.domain.Author;
import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Comment;
import ru.otus.jpalibrary.domain.Genre;
import ru.otus.jpalibrary.repositories.comm.ContactTypeEn;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;


@ShellComponent
public class ShellService {

    private final BookService bookService;
    private final ConsoleService consoleService;
    private  UserService userService;

    @Autowired
    public ShellService(BookService bookService, ConsoleService consoleService, UserService userService) {
        this.bookService = bookService;
        this.consoleService = consoleService;
        this.userService = userService;
    }

    @ShellMethod(value="help", key = {"h","hlp"})
    public void help() {
        consoleService.printHelp();
    }

    @ShellMethod(value="login", key = {"l","lgn"})
    public void login(@ShellOption String contactType,@ShellOption String login, @ShellOption String password) {
            userService.connectBy(ContactTypeEn.getContactTypeEn(contactType),login,password);
    }

    @ShellMethod(value="add genre", key = {"ag"})
    @ShellMethodAvailability("isAuthFun")
    public void getGenre(@ShellOption String genreName) {
        Genre genre = bookService.getFindedOrCreatedGenreByName(genreName);
        consoleService.printGenre(genre);
    }

    @ShellMethod(value="print genres", key = {"pg"})
    @ShellMethodAvailability("isAuthFun")
    public void printGenres() {
        List<Genre> genres = bookService.getAllGenres();
        consoleService.printGenreList(genres);
    }

    @ShellMethod(value="add author", key = {"aa"})
    @ShellMethodAvailability("isAuthFun")
    public void getAuthorsList(@ShellOption String lastName
            , @ShellOption String firstName
            , @ShellOption(defaultValue = "") String middleName
            , @ShellOption Date birthDate) {
        Author author = bookService.getFindedOrCreatedAuthorsByName(lastName, firstName, middleName,birthDate);
        consoleService.printAuthor(author);
    }

    @ShellMethod(value="print all authors", key = {"pa"})
    @ShellMethodAvailability("isAuthFun")
    public void printAuthors() {
        List<Author> authors = bookService.getAllAuthors();
        consoleService.printAuthorList(authors);
    }

    @ShellMethod(value="add new book", key = {"anb"})
    @ShellMethodAvailability("isAuthFun")
    public void addBook(@ShellOption String bookName, @ShellOption Integer issueYear, @ShellOption long authorId, @ShellOption int genreId) {
        Book book = bookService.createNewBookIsSameNotExist(bookName, issueYear, authorId, genreId);
        consoleService.printBookInfo(book);
    }

    @ShellMethod(value="print all books", key = {"pab"})
    @ShellMethodAvailability("isAuthFun")
    public void printAllBooks() {
        List<Book> books = bookService.getAllBooks();
        consoleService.printAllBooks(books);
    }

    @ShellMethod( value = "add comment to book", key = {"acb"})
    @ShellMethodAvailability("isAuthFun")
    public void addCommentToBook(@ShellOption long bookId, @ShellOption String comment) {
        Comment comment1 = bookService.addCommentToBook(bookId, comment, userService.getUser() );
        consoleService.printCommentConfirm(comment1);
    }

    @ShellMethod( value = "print book comments", key = {"pbc"})
    @ShellMethodAvailability("isAuthFun")
    public void printBookComments(@ShellOption long bookId) {
        Book book = bookService.getBookById(bookId);
        List<Comment> comments = bookService.getBookComments(book);
        consoleService.printBookComments(book, comments);
    }

    private Availability isAuthFun() {
        return (userService != null && userService.isConnect()) ? Availability.available() : Availability.unavailable("Необходимо залогиниться");
    }
}
