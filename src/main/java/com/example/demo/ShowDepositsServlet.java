package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "show_deposits", value = "/show_deposits")
public class ShowDepositsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mariadb://localhost/bar", "root", "123pass");
            // Retrieve the account number from the Servlet Context
            ServletContext context = getServletContext();
            Object obj = context.getAttribute("accno");
            String accno = obj.toString();

            pst = con.prepareStatement("SELECT date, amount FROM deposits WHERE accno = ? ORDER BY date DESC");
            pst.setString(1, accno);

            rs = pst.executeQuery();

            // Use StringBuilder to build the HTML table dynamically
            StringBuilder tableHtml = new StringBuilder();

            while (rs.next()) {
                String dateStr = rs.getString("date");
                double amount = rs.getDouble("amount");

                // Convert date string to LocalDate
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));

                // Append each deposit record to the table
                tableHtml.append("<tr>")
                        .append("<td>").append(date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))).append("</td>")
                        .append("<td>").append(amount).append("</td>")
                        .append("</tr>");
            }

            // Forward the HTML table to the JSP page
            request.setAttribute("depositTable", tableHtml.toString());
            request.getRequestDispatcher("show_deposits.jsp").forward(request, response);

        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Error retrieving deposit history.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

