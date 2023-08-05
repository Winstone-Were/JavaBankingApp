package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet3")
public class servlet3 extends HttpServlet {

    // Define a fixed thread pool with a single thread
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Connection con;
            PreparedStatement pst;
            ResultSet rs;

            response.setContentType("text/html");

            ServletContext context = getServletContext();
            Object obj = context.getAttribute("accno");
            String accno = obj.toString();
            String amount = request.getParameter("amount");

            con = DriverManager.getConnection("jdbc:mariadb://localhost/bar", "root", "123pass");
            pst = con.prepareStatement("SELECT mdeposit FROM account_holder WHERE accnum = ?");
            pst.setString(1, accno);

            rs = pst.executeQuery();

            if (rs.next()) {
                // If account number exists, update the balance (mdeposit + amount)
                int existingBalance = rs.getInt("mdeposit");
                int newBalance = existingBalance + Integer.parseInt(amount);

                pst = con.prepareStatement("UPDATE account_holder SET mdeposit = ? WHERE accnum = ?");
                pst.setInt(1, newBalance);
                pst.setString(2, accno);
                int rows = pst.executeUpdate();

                if (rows == 1) {
                    out.println("Your deposit has been added. New balance: " + newBalance);

                    // Submit the deposit record to DepositProcessor asynchronously
                    executor.submit(new DepositProcessor(accno, amount));
                } else {
                    out.println("Transaction failed.");
                }
            } else {
                // If account number does not exist, insert a new row
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                String date = df.format(now);

                pst = con.prepareStatement("INSERT INTO account_holder (accnum, date, mdeposit) VALUES (?, ?, ?)");
                pst.setString(1, accno);
                pst.setString(2, date);
                pst.setString(3, amount);
                int rows = pst.executeUpdate();

                if (rows == 1) {
                    out.println("Account created with initial deposit.");

                    // Submit the deposit record to DepositProcessor asynchronously
                    executor.submit(new DepositProcessor(accno, amount));
                } else {
                    out.println("Account creation failed.");
                }
            }

        } catch (SQLException ex) {
            out.println(ex);
            ex.printStackTrace();
        }

    }

    // DepositProcessor class to insert deposit records into the 'deposits' table
    private class DepositProcessor implements Runnable {
        private String accno;
        private String amount;

        public DepositProcessor(String accno, String amount) {
            this.accno = accno;
            this.amount = amount;
        }

        @Override
        public void run() {
            try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost/bar", "root", "123pass")) {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                String date = df.format(now);

                PreparedStatement pst = con.prepareStatement("INSERT INTO deposits (accno, date, amount) VALUES (?, ?, ?)");
                pst.setString(1, accno);
                pst.setString(2, date);
                pst.setString(3, amount);
                int rows = pst.executeUpdate();

                if (rows == 1) {
                    System.out.println("Deposit record inserted successfully.");
                } else {
                    System.out.println("Failed to insert deposit record.");
                }   
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
