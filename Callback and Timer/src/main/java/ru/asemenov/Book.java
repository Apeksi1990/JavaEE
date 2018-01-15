package ru.asemenov;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static ru.asemenov.Book.FIND_ALL;

@Data
@Entity
@NamedQuery(name = FIND_ALL, query = "select b from Book b")
public class Book implements Serializable{
    public static final String FIND_ALL = "Book.findAllBooks";
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String title;
    private Float price;
    @Size(max = 2000)
    @Column(length = 2000)
    private String description;
    private String isbn;
    private Integer nbOfPage;
    private Boolean illustrations;

    public Book() {
    }

    public Book(String title, String description, Float price, String isbn, Integer nbOfPage, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
    }

    public Book(String title, Float price, String description, String isbn, Integer nbOfPage, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
    }
}
