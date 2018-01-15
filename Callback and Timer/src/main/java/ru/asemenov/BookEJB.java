package ru.asemenov;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

import static ru.asemenov.Book.FIND_ALL;

@Stateless
@LocalBean
public class BookEJB implements BookEJBRemote {

    @Inject
    private EntityManager em;

    public List<Book> findBooks() {
	TypedQuery<Book> query = em.createNamedQuery(FIND_ALL, Book.class);
	return query.getResultList();
    }

    public @NotNull Book createBook(Book book) {
        em.persist(book);
	return book;
    }

    public void deleteBook(@NotNull Book book) {
        em.remove(em.merge(book));
    }

    public @NotNull Book updateBook(@NotNull Book book) {
	return em.merge(book);
    }
}
