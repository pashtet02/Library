/*
package com.epam.jt.name.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    private static final String  URL = "jdbc:mysql://localhost:3306/library?user=root&password=root&serverTimezone=UTC";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String msg = "JDBC Pool connection successfully created";
        Connection con = null;
        try {
            String dataSourceName = getServletContext().getInitParameter("DataSource");
            Context context = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            System.out.println(dataSource.getClass().getName());
            con = DriverManager.getConnection(URL);
            System.out.println(con);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<h1>Hello World</h1>");
        out.write("<hr/>");
        out.write("<p>" + msg + "</p>");
        out.close();
    }
}
*/
