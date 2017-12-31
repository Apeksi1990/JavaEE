package ru.asemenov;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllBooks", query = "select b from Book b"),
        @NamedQuery(name = "findBookH2G2", query = "select b from Book b where b.title = 'H2G2'")
})
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String title;
    private Float price;
    @Size(min = 10, max = 2000)
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
}
