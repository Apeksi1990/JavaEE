package ru.asemenov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookIT {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04TestPU");
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() throws Exception {
        if (em != null) em.close();
    }

    @Test
    public void shouldFindJavaEE7Book() throws Exception {
        Book book = em.find(Book.class, 1001L);
        assertEquals("Изучаем Java EE 7", book.getTitle());
    }

    @Test
    public void shouldCreateH2G2Book() throws Exception {
        Book book = new Book("H2G2", "Автостопом по галактике", 12.5F, "1-84023-742-2", 354, false);

        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull("ID не может быть пустым", book.getId());

        List<Book> books = em.createNamedQuery("findBookH2G2", Book.class).getResultList();
        assertEquals(1, books.size());
        book = em.createNamedQuery("findBookH2G2", Book.class).getSingleResult();
        assertEquals("Автостопом по галактике", book.getDescription());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationCauseNullTitle() {
        Book book = new Book(null, "Пустое название, ошибка", 12.5F, "1-84023-742-2", 354, false);
        em.persist(book);
    }
}
