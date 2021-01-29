package com.epam.jt.name.dao;

import com.epam.jt.name.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.epam.jt.name.dao.SQLConstants.*;


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
        ResultSet rs = null;
        Book book = new Book();

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM books where id=?")) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                book.setId(rs.getLong(ID));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setISBN(rs.getLong("ISBN"));
                book.setNumber(rs.getInt("number"));
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("language"));
            }
        } catch (SQLException throwable) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Hello");
        } finally {
            close(rs);
        }
        return book;
    }

    public List<Book> getAllSortedBy(String columb) throws SQLException {
        Connection con = getConnection();
        List<Book> books = new ArrayList<>();
        books = getAllBooks(con, "SELECT * FROM books ORDER BY " + columb);
        return books;
    }

    public List<Book> getSomeBooks(int start, int total) throws SQLException {
        Connection con = getConnection();
        List<Book> books;
        books = getAllBooks(con, "select * from books limit " + (start - 1) + "," + total);
        return books;
    }

    private static List<Book> getAllBooks(Connection con, String query) throws SQLException {
        List<Book> books = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

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
    public List<Book> getAll() throws SQLException {
        List<Book> books;

        Connection con = getConnection();
        books = getAllBooks(con, SELECT_ALL_BOOKS);
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
    public void update(Book book) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(UPDATE_BOOK)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setLong(3, book.getISBN());
            preparedStatement.setString(4, book.getPublisher());
            preparedStatement.setInt(5, book.getNumber());
            preparedStatement.setString(6, book.getLanguage());
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, throwable.getSQLState(), throwable);
        }
    }

    @Override
    public void delete(Book book) {
        //hello
    }

    private static Book mapBook(ResultSet rs) {
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

    public Book getByTitle(String title) {
        ResultSet rs = null;
        Book book = new Book();

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(GET_BOOK_BY_TITLE)) {
            statement.setString(1, title);
            rs = statement.executeQuery();
            while (rs.next()) {
                book = mapBook(rs);
            }

        } catch (SQLException throwable) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Hello");
        } finally {
            close(rs);
        }
        return book;
    }
}
