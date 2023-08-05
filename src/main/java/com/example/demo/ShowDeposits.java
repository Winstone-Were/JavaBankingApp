package com.example.demo;

import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="showdeposits" ,value = "/showdeposits")
public class ShowDeposits extends HelloServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        out.println("<html><body>");
        try
        {

            ServletContext context = getServletContext();
            Object obj = context.getAttribute("accno");

            Connection con = DBConnection.GetConnection();
            // Here dsnname- mydsn,user id- system(for oracle 10g),password is pintu.
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from account_holder where accnum = ' " + obj.toString() +" '");
            out.println("<table%>");
            out.println("<tr><th>EmpId</th><th>EmpName</th><th>Salary</th><tr>");
            while (rs.next())
            {
                String n = rs.getString("date");
                String nm = rs.getString("mdeposit");
                //int s = rs.getInt("sal");
                out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>");
            }
            out.println("</table>");
            out.println("</html></body>");
        }
        catch (Exception e)
        {
            out.println(e);
        }
    }

}
