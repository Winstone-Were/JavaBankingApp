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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/transactServlet")
public class TransactServlet extends HttpServlet {

    // Define a fixed thread pool with a single thread
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mariadb://localhost/bar", "root", "123pass");

            String toAccNo = request.getParameter("toAccNo");
            String amountStr = request.getParameter("amount");
            int amount = Integer.parseInt(amountStr);

            // Get the "fromAccNo" from the Servlet Context
            String fromAccNo = (String) getServletContext().getAttribute("accno");

            // Check if "toAccNo" and "fromAccNo" exist in the account_holder table
            pst = con.prepareStatement("SELECT * FROM account_holder WHERE accnum = ?");
            pst.setString(1, fromAccNo);
            boolean fromAccountExists = pst.executeQuery().next();

            pst = con.prepareStatement("SELECT * FROM account_holder WHERE accnum = ?");
            pst.setString(1, toAccNo);
            boolean toAccountExists = pst.executeQuery().next();

            if (!fromAccountExists) {
                out.println("From Account Number does not exist.");
            } else if (!toAccountExists) {
                out.println("To Account Number does not exist.");
            } else {
                // Perform the transaction update in a separate thread
                executor.submit(new TransactionProcessor(con, fromAccNo, toAccNo, amount));
                response.sendRedirect("showTransactions");
                out.println("Transaction Successful. Transfer Amount: " + amount);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Error performing transaction.");
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pst != null)
                    pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // TransactionProcessor class to insert transaction records into the 'Transactions' table
    private class TransactionProcessor implements Runnable {
        private Connection con;
        private String fromAccNo;
        private String toAccNo;
        private int amount;

        public TransactionProcessor(Connection con, String fromAccNo, String toAccNo, int amount) {
            this.con = con;
            this.fromAccNo = fromAccNo;
            this.toAccNo = toAccNo;
            this.amount = amount;
        }

        @Override
        public void run() {
            try {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                LocalDate date = LocalDate.now();
                String dateString = df.format(date);

                // Update "fromAccNo" balance (deducting the transfer amount)
                PreparedStatement pst = con.prepareStatement("UPDATE account_holder SET mdeposit = mdeposit - ? WHERE accnum = ?");
                pst.setInt(1, amount);
                pst.setString(2, fromAccNo);
                pst.executeUpdate();

                // Update "toAccNo" balance (adding the transfer amount)
                pst = con.prepareStatement("UPDATE account_holder SET mdeposit = mdeposit + ? WHERE accnum = ?");
                pst.setInt(1, amount);
                pst.setString(2, toAccNo);
                pst.executeUpdate();

                // Insert transaction record into the 'Transactions' table
                pst = con.prepareStatement("INSERT INTO Transactions (from_acc_no, to_acc_no, ammount, date) VALUES (?, ?, ?, ?)");
                pst.setString(1, fromAccNo);
                pst.setString(2, toAccNo);
                pst.setInt(3, amount);
                pst.setString(4, dateString);
                pst.executeUpdate();

                System.out.println("Transaction processed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        // Shutdown the executor service when the servlet is destroyed
        executor.shutdown();
        super.destroy();
    }
}
