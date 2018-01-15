package ru.asemenov;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface BookEJBRemote {
    List<Book> findBooks();
    Book createBook(Book book);
    void deleteBook(Book book);
    Book updateBook(Book book);
}
