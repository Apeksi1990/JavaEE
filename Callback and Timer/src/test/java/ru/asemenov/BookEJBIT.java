package ru.asemenov;

import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookEJBIT {
    @Test
    public void shouldCreateABook() throws Exception {
	Map<String, Object> properties = new HashMap<>();
	properties.put(EJBContainer.MODULES, new File("target/classes"));
	try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {
	    Context ctx = ec.getContext();

	    //Проверяет зависимости JNDI (источники данных и EJB)
	    assertNotNull(ctx.lookup("java:global/jdbc/chapter08DS"));
	    assertNotNull(ctx.lookup("java:global/classes/BookEJB!ru.asemenov.BookEJBRemote"));
	    assertNotNull(ctx.lookup("java:global/classes/BookEJB!ru.asemenov.BookEJB"));

	    BookEJB bookEJB = (BookEJB) ctx.lookup("java:global/classes/BookEJB!ru.asemenov.BookEJB");
	    assertEquals(2, bookEJB.findBooks().size());

	    Book book = new Book("H2G2", 12.5F, "Научная фантастика", "1-24561-799-0", 354, false);
	    book = bookEJB.createBook(book);
	    assertNotNull("ID не может быть пустым", book.getId());
	    assertEquals(3, bookEJB.findBooks().size());
	    bookEJB.deleteBook(book);
	    assertEquals(2, bookEJB.findBooks().size());
	}
    }
}
