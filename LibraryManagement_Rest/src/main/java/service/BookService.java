package service;

import model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookById(int bookId);
    Book createBook(Book book);
    Book updateBook(Book book);
    Book deleteBook(Book book);
}
