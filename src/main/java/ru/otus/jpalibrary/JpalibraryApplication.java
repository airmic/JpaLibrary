package ru.otus.jpalibrary;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class JpalibraryApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(JpalibraryApplication.class, args);
        Console.main(args);
    }

}
