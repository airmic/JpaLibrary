package ru.otus.jpalibrary.service;

import org.springframework.stereotype.Service;
import ru.otus.jpalibrary.domain.Author;
import ru.otus.jpalibrary.domain.Book;
import ru.otus.jpalibrary.domain.Comment;
import ru.otus.jpalibrary.domain.Genre;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    @Override
    public void printHelp() {
        System.out.println("Для работы необходимо залогиниться (команда l  или login)");
        System.out.println("Формат: l <contactType (email|phone)> <username> <password>\n\t\tдля входа: l email vasya@mail.ru 123");
        System.out.println("Добавление или поиск жанра: ag <название жанра>");
        System.out.println("Информация по всем жанрам: pg");
        System.out.println("Добавление или поиск автора: аа <Фамилия> <Имя> <Отчетво> <Дата рождения YYYY-MM-DD>");
        System.out.println("Информация по все авторам: pa");
        System.out.println("Добавление новой книги: anb <Название> <год издания> <ИД автора> <ИД жанра>");
        System.out.println("Информация по всем книгам: pab");
        System.out.println("Добавить комментарий к книге: acb <Ид книги> <Комментарий>");
        System.out.println("Печать все комментарии книги: pbc <Ид книги>");
    }

    @Override
    public void printGenreList(List<Genre> genres) {
        System.out.println("Жанры доступные на данный момент\n");
        for(Genre genre : genres) {
            System.out.println(String.format("%d\t\t - \t\t%s", genre.getId(), genre.getGenreName()));
        }
    }

    @Override
    public void printGenre(Genre genre) {
        System.out.println(String.format("Информация по указанному жанру:\n\t %d \t - \t %s\n",genre.getId(), genre.getGenreName()));
    }

    @Override
    public void printAuthor(Author author) {
        System.out.println("Информация об авторе:\n");
        System.out.println(String.format("%d\t\t - \t\t%s %s %s %s", author.getId(), author.getLastName(), author.getFirstName(), ofNullable(author.getMiddleName()).orElse(""), new SimpleDateFormat("dd.MM.yyyy").format(author.getBirthDate())));
    }

    @Override
    public void printAuthorList(List<Author> authors) {
        System.out.println("Список авторов:\n");
        for(Author author : authors) {
            System.out.println(String.format("%d\t\t - \t\t%s %s %s %s", author.getId(), author.getLastName(), author.getFirstName(), ofNullable(author.getMiddleName()).orElse(""), new SimpleDateFormat("dd.MM.yyyy").format(author.getBirthDate())));
        }
    }

    @Override
    public void printBookInfo(Book book) {
        System.out.println("Информация по введенной книге\n");
        System.out.println(String.format("ID - %d\nНазвание - %s\nГод издания - %d\n", book.getId(), book.getBookName(), book.getIssueYear()));
        System.out.println("Относится к жанру:");
        System.out.println(book.getGenre().getGenreName());
        System.out.println("Авторы:");
        for(Author author : book.getAuthors()) {
            System.out.println(String.format("\t %s %s %s", author.getLastName(), author.getFirstName(), ofNullable(author.getMiddleName()).orElse("")));
        }
    }

    @Override
    public void printAllBooks(List<Book> books) {
        System.out.println(" Информация по имеющимся книгам");
        for(Book book : books)
            printBookInfo(book);
    }

    @Override
    public void printCommentConfirm(Comment comment) {
        System.out.println("Книга: "+comment.getBook().getBookName());
        System.out.println("Добавлен комментарий:\n\t"+comment.getText());
    }

    @Override
    public void printBookComments(Book book, List<Comment> comments) {
        System.out.println("Книга: "+book.getBookName());
        System.out.println("Комментарии:");
        comments.forEach(comment -> System.out.println(String.format("\n\n\t - %s\t%s\n\t%s",comment.getUser().getNick()
                , new SimpleDateFormat("dd.mm.yyyy").format(comment.getCreatedDt()), comment.getText())));
    }
}
