package model;

public class Book {
    int bookId;
    String bookName;
    String author;
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBook_name() {
        return bookName;
    }

    public void setBook_name(String book_name) {
        this.bookName = book_name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + bookId +
                ", book_name='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
