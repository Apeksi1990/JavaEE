package ru.asemenov;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import ru.asemenov.models.Book;
import ru.asemenov.services.BookService;

public class Main {
    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        BookService bookService = container.instance().select(BookService.class).get();
        Book book = bookService.createBook("H2G2", 12.5f, "Интересная книга");
        System.out.println(book);
        weld.shutdown();
    }
}
