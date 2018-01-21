package ru.asemenov;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

//@Singleton
//@Startup
/*@DataSourceDefinition(
		className = "org.postgresql.Driver",
		name = "jdbc/chapter08DS",
		user = "postgres",
		password = "postgres",
        portNumber=5432,
		databaseName = "Books",
		properties = {"connectionAttributes ="}
)*/
public class DatabasePopulator {
    @Inject
    private BookEJB bookEJB;
    private Book h2g2;
    private Book lord;
    @PostConstruct
    private void populateDB() {
        h2g2 = new Book("Изучаем Java EE 7", 35F, "Великая книга", "1-8763-9125-7", 605, true);
        lord = new Book("Властелин колец", 50.4F, "Фентези", "1-84023-742-2", 1216, true);
        bookEJB.createBook(h2g2);
        bookEJB.createBook(lord);
    }
    @PreDestroy
    private void clearDB() {
        bookEJB.deleteBook(h2g2);
        bookEJB.deleteBook(lord);
    }
}
