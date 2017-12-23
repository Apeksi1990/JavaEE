package ru.asemenov.services;

import ru.asemenov.log.Loggable;
import ru.asemenov.models.Book;
import ru.asemenov.number.NumberGenerator;
import ru.asemenov.number.ThirteenDigits;

import javax.inject.Inject;

@Loggable
public class BookService {
    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    public Book createBook(String title, Float price, String description) {
        Book book = new Book(title, price, description);
        book.setNumber(numberGenerator.generateNumber());
        return book;
    }
}
