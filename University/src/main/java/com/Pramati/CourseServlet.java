package com.Pramati;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CourseServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException {
        PrintWriter pw=resp.getWriter();
        Data d=new Data();
        pw.print(d.getStudent_name());
        /*for(int i=0;i<d.getSt().size();i++) {
            pw.print(d.getSt().get(i).getStudent_id() + " " + d.getSt().get(i).getStudent_name() + " ");

            for (int j = 0; j < d.getSt().get(i).getCr().size(); j++) {
                pw.print("\n");
                pw.print(d.getSt().get(i).getCr().get(j).course_name);
            }
            pw.print("\n");

        }*/
        /*pw.print(d.deleteStudentdata());
        pw.print(d.getSt());*/
    }
}
