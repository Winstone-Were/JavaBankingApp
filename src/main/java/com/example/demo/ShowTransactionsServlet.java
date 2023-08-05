package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ShowTransactionsServlet", value = "/show_transactions")
public class ShowTransactionsServlet extends HttpServlet {

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

            pst = con.prepareStatement("SELECT date, ammount, from_acc_no, to_acc_no FROM Transactions WHERE from_acc_no = ? OR to_acc_no = ? ORDER BY date DESC");
            pst.setString(1, accno);
            pst.setString(2, accno);

            rs = pst.executeQuery();

            // Use StringBuilder to build the HTML table dynamically
            StringBuilder tableHtml = new StringBuilder();
            StringBuilder usdTableHtml = new StringBuilder();
            StringBuilder eurTableHtml = new StringBuilder();

            CurrencyConverter usdConverter = new CurrencyAdapter("USD");
            CurrencyConverter eurConverter = new CurrencyAdapter("EUR");

            while (rs.next()) {
                String dateStr = rs.getString("date");
                double amount = rs.getDouble("ammount");
                String fromAccNo = rs.getString("from_acc_no");
                String toAccNo = rs.getString("to_acc_no");

                // Convert date string to LocalDate
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));

                double amountInUSD = usdConverter.convertAmount((int)amount);
                double amountInEUR = eurConverter.convertAmount((int)amount);
                // Append each transaction record to the table
                tableHtml.append("<tr>")
                        .append("<td>").append(date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))).append("</td>")
                        .append("<td>").append(amount).append("</td>")
                        .append("<td>").append(fromAccNo).append("</td>")
                        .append("<td>").append(toAccNo).append("</td>")
                        .append("</tr>");
                usdTableHtml.append("<tr>")
                        .append("<td>").append(date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))).append("</td>")
                        .append("<td>").append(amountInUSD).append("</td>")
                        .append("<td>").append(fromAccNo).append("</td>")
                        .append("<td>").append(toAccNo).append("</td>")
                        .append("</tr>");

                eurTableHtml.append("<tr>")
                        .append("<td>").append(date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))).append("</td>")
                        .append("<td>").append(amountInEUR).append("</td>")
                        .append("<td>").append(fromAccNo).append("</td>")
                        .append("<td>").append(toAccNo).append("</td>")
                        .append("</tr>");
            }

            // Forward the HTML table to the JSP page
            request.setAttribute("transactionTable", tableHtml.toString());
            request.setAttribute("USD_table",usdTableHtml.toString());
            request.setAttribute("EUR_table",eurTableHtml.toString());
            request.getRequestDispatcher("show_transactions.jsp").forward(request, response);

            out.flush();
            out.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Error retrieving transaction history.");
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
