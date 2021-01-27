package com.epam.jt.name.dao;

import com.epam.jt.name.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.epam.jt.name.dao.SQLConstants.SELECT_ALL_BOOKS;
import static com.epam.jt.name.dao.SQLConstants.SQL_ADD_NEW_BOOK;


public class BookDao implements Dao<Book> {
    private static BookDao bookDao;

    public static synchronized BookDao getInstance() {
        if (bookDao == null) {
            bookDao = new BookDao();
        }
        return bookDao;
    }

    private BookDao() {
        // hello everyone
    }

    @Override
    public Book get(long id) {
        return null;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_BOOKS)) {

            while (rs.next()) {
                books.add(mapBook(rs));
            }
        } catch (SQLException ex) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "MESSAGE");
            throw ex;
        }
        return books;
    }

    @Override
    public void save(Book book) {
        ResultSet rs = null;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL_ADD_NEW_BOOK,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setLong(3, book.getISBN());
            pstmt.setString(4, book.getPublisher());
            pstmt.setInt(5, book.getNumber());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    book.setId(rs.getLong(1));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            close(rs);
        }
    }

    @Override
    public void update(Book book, String[] params) {
        //hello
    }

    @Override
    public void delete(Book book) {
        //hello
    }

    private Book mapBook(ResultSet rs) {
        Book book = new Book();
        try {
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPublisher(rs.getString("publisher"));
            book.setISBN(rs.getLong("ISBN"));
            book.setNumber(rs.getInt("number"));
            book.setPublishingDate(rs.getDate("publishingDate"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return book;
    }

    private void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception ex) {
                throw new IllegalStateException("Cannot close " + ac);
            }
        }
    }

}
