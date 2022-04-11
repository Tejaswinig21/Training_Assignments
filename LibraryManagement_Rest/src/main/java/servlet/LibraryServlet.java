package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Book;
import service.BookService;
import service.InMemoryBookServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LibraryServlet extends HttpServlet {
    private static final Gson gson=new GsonBuilder().create();
    private static final BookService bookservice=new InMemoryBookServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getPathInfo()==null){
            List<Book> books=bookservice.getBooks();
            resp.setStatus(200);
            resp.setHeader("Content-Type:","application/json");
            resp.getOutputStream().println(gson.toJson(books));
        }
        else{
            int bookId=Integer.parseInt(req.getPathInfo().replace("/",""));
            Book book=bookservice.getBookById(bookId);
            if(book!=null){
                resp.setStatus(200);
                resp.setHeader("Content-Type","application/json");
                resp.getOutputStream().println(gson.toJson(book));
            }
            else {
                resp.setStatus(404);
            }
        }
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        Book book=gson.fromJson(req.getReader(),Book.class);
        bookservice.createBook(book);
        resp.setStatus(201);
        resp.setHeader("Content-type","application/json");
        resp.getOutputStream().println(gson.toJson(book));

    }
    protected void doPut(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        Book book=gson.fromJson(req.getReader(),Book.class);
        bookservice.updateBook(book);
        resp.setStatus(201);
        resp.setHeader("Content-type","application/json");
        resp.getOutputStream().println(gson.toJson(book));
    }
    protected void doDelete(HttpServletRequest req,HttpServletResponse res) throws IOException{
        Book book=gson.fromJson(req.getReader(),Book.class);
        bookservice.deleteBook(book);
        res.setStatus(201);
        res.setHeader("Content-type","application/json");
        res.getOutputStream().println(gson.toJson(book));
    }
}
