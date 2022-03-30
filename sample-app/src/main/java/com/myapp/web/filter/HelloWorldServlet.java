package com.myapp.web.filter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*resp.setHeader("sampleHeader", "val");
        resp.getOutputStream().write("okay bye.....".getBytes());*/

        PrintWriter pw=resp.getWriter();
        pw.print("Hello!");
    }
}
