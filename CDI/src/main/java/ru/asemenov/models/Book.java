package ru.asemenov.models;

import lombok.Data;

@Data
public class Book {
    private String title;
    private Float price;
    private String description;
    private String number;

    public Book(String title, Float price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }
}
