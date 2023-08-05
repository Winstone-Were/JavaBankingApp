package com.example.demo;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfirmLogin {
    static boolean CheckLogin(String arg1, String arg2) throws SQLException {
        String result;

        String accno = arg1;
        String pinno = arg2;
        PreparedStatement pst = DBConnection.GetConnection().prepareStatement("select * from login where accno = ? and pinno = ?");
        pst.setString(1, accno);
        pst.setString(2, pinno);
        ResultSet rs = pst.executeQuery();
        boolean row = false;
        row = rs.next();

        return (row);
    }
}
