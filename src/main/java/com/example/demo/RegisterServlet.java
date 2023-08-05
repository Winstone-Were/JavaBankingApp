package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="registerservlet" ,value = "/registerservlet")
public class RegisterServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/bar";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123pass";

    Connection conn = DBConnection.GetConnection();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accno = request.getParameter("accno");
        String pinno = request.getParameter("pinno");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        Connection conn = null;
        PreparedStatement stmt = null;
        PrintWriter out = response.getWriter();

        try {
            conn = DBConnection.GetConnection();

            String sql = "INSERT INTO login (accno, pinno, fname, lname) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, accno);
            stmt.setString(2, pinno);
            stmt.setString(3, firstname);
            stmt.setString(4, lastname);

            stmt.executeUpdate();

            out.println("<html><body><h2>Registration Successful</h2></body></html>");
            response.sendRedirect("index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<html><body><h2>Registration Failed</h2></body></html>");

        } finally {
            try {
                if (stmt != null) stmt.close();
                //if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}