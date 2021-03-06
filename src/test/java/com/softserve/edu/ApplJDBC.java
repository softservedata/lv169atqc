package com.softserve.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplJDBC {
    private static Connection con = null;
    private static String username = "ssu-oms";
    private static String password = "ssu-oms";
//    private static String username = "db169";
//    private static String password = "db169";
//    private static String username = "root";
//    private static String password = "root";
    // Microsoft
    //private static String URL = "jdbc:sqlserver://CLASS02.training.local\\SQLEXPRESS;databasename=Lv169OMS;";
    //private static String URL = "jdbc:sqlserver://ssu-sql12\\tc;databasename=ssu-oms;";
    // Sybase
    private static String URL = "jdbc:jtds:sqlserver://ssu-sql12/ssu-oms;instance=tc;";
    //private static String URL = "jdbc:jtds:sqlserver://CLASS02/Lv169OMS;instance=SQLEXPRESS;";
    // MySQL
//    private static String URL = "jdbc:mysql://localhost:3306/measurement_devices";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Start...");
        //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        //DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
        //
        DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
        //Class.forName("net.sourceforge.jtds.jdbc.Driver");
        //
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Class.forName("com.mysql.jdbc.Driver");
        // Load the driver
        con = DriverManager.getConnection(URL, username, password);
        if (con != null) {
            System.out.println("Connection Successful! \n"); }
        if (con == null) {
            System.exit(0); }
        Statement st = con.createStatement();
        // Statement allows you to send inquiries database
        //ResultSet rs = st.executeQuery("select * from Users");
        //ResultSet rs = st.executeQuery("SELECT ID, Login, Password, FirstName, LastName, Email, RegionRef, RoleRef FROM dbo.Users WHERE Login = 'aaai';");
        //ResultSet rs = st.executeQuery("SELECT * FROM dbo.Users;");
        //
        //st.execute("INSERT INTO dbo.Users (Login, Password, FirstName, LastName, Email, RegionRef, RoleRef, IsUserActive) VALUES ('iva', 'qwerty', 'ivanka', 'horoshko', 'mail@gmail.com', '1', '1', '1');");
        //ResultSet rs = st.executeQuery("SELECT * FROM users WHERE Login='iva';");
        //ResultSet rs = st.executeQuery("SELECT *  FROM Users;");
        //ResultSet rs = st.executeQuery("SELECT *  FROM Users WHERE Login LIKE 'iv%';");
        //st.execute("DELETE dbo.Users WHERE ID='1001561'");
        ResultSet rs = st.executeQuery("SELECT ID, *  FROM Users WHERE Login LIKE 'cc%';");
        // MySQL
        //ResultSet rs = st.executeQuery("SELECT *  FROM User;");
        //st.execute("INSERT INTO user (username,isAvailable,password,firstName,lastName,organizationId) VALUES ('verificator-lv2',1,'$2a$10$59Mv7tEUrVH8iBeDsm9y7.zUcJoPHnnyOvMnC4zKRV8.wlnugQ2G2','first-lv2','last-lv2',7);");
        //st.execute("INSERT INTO user_role (username,value) VALUES ('verificator-lv2','STATE_VERIFICATOR_ADMIN');");
        //
        //ResultSet rs = st.executeQuery("SELECT *  FROM User WHERE username LIKE 'verifi%';");
        // ResultSet gets the result table
        int x = rs.getMetaData().getColumnCount();
        // Resultset.getMetaData () get the information
        // output file
        while (rs.next()) {
            for (int i = 1; i <= x; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
        System.out.println();
        if (rs != null)
            rs.close();
        if (st != null)
            st.close();
        if (con != null)
            con.close();
    }
}
