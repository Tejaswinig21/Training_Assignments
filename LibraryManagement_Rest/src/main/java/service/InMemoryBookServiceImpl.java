package service;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBookServiceImpl implements BookService{
    private List<Book> books=new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Book getBookById(int bookId) {
        for(int i=0;i<books.size();i++){
            if(books.get(i).getBookId()==bookId){
                return books.get(i);
            }
        }
       // for(Book b:books)
        return null;
    }

    @Override
    public Book createBook(Book book) {
        books.add(book);
        return book;

    }

    @Override
    public Book updateBook(Book book) {
        for(int i=0;i<books.size();i++){
            if(books.get(i).getBookId()==book.getBookId()){
                books.set(i,book);
            }
        }
        return book;
    }
    public Book deleteBook(Book book){
        for (int i=0;i<books.size();i++){
            if(books.get(i).getBookId()==book.getBookId()){
                books.remove(book);
            }
        }
        return book;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }



}
