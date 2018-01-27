package ru.asemenov;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws NamingException {

        // Looks up the EJB
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        /*props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");*/
        Context ctx = new InitialContext();
        BookEJBRemote bookEJB = (BookEJBRemote) ctx.lookup("java:global/Callback_and_Timer-1.0-SNAPSHOT/BookEJB!ru.asemenov.BookEJBRemote");

        // Gets and displays all the books from the database
        List<Book> books = bookEJB.findBooks();
        for (Book aBook : books) {
            System.out.println("--- " + aBook);
        }

        // Creates an instance of book
        Book book = new Book("The Hitchhiker's Guide to the Galaxy", 12.5F, "Science fiction by Douglas Adams.", "1-24561-799-0", 354, false);

        book = bookEJB.createBook(book);
        System.out.println("### Book created : " + book);

        /*book.setTitle("H2G2");
        book = bookEJB.updateBook(book);
        System.out.println("### Book updated : " + book);*/

        /*bookEJB.deleteBook(book);
        System.out.println("### Book deleted");*/
    }
}
