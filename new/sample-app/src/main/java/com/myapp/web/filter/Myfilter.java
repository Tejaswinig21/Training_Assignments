package com.myapp.web.filter;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.*;
import java.util.logging.Logger;


public class Myfilter implements Filter {
    public void init(FilterConfig arg0) throws ServletException{}
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        Logger logger = (Logger) LoggerFactory.getLogger(Myfilter.class);

        PrintWriter out=resp.getWriter();

        String password=req.getParameter("password");
        if(password.equals("admin")){
            chain.doFilter(req, resp);//sends request to next resource
        }
        else{
            out.print("username or password error!");
        }

    }
    public void destroy() {}

}
