package com.library.name.dao;

import com.library.name.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class BookDao implements Dao<Book> {
    private static BookDao bookDao;
    private static final Logger logger = Logger.getLogger(BookDao.class);

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
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setISBN(rs.getLong("ISBN"));
                book.setNumber(rs.getInt("number"));
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("language"));
            }
        } catch (SQLException throwable) {
            logger.error("get book method exception: " + throwable.getSQLState());
        } finally {
            close(rs);
        }
        logger.info("get() book get successfully book id: " + book.getId());
        return book;
    }

    public List<Book> getAllSortedBy(String columb) throws SQLException {
        Connection con = getConnection();
        List<Book> books;
        books = getAllBooks(con, "SELECT * FROM books ORDER BY " + columb);
        return books;
    }

    public List<Book> getSomeBooks(int start, int numberOfBooks, String orderParam) throws SQLException {
        Connection con = getConnection();
        List<Book> books;
        books = getAllBooks(con, "select * from books order by + " + orderParam +" limit " + (start - 1 ) * numberOfBooks + "," + numberOfBooks);
        return books;
    }

    private static List<Book> getAllBooks(Connection con, String query) throws SQLException {
        List<Book> books = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                books.add(mapBook(rs));
            }
            if (books.size() > 0) {
                logger.info("getAllBooks() get successfully number of books: " + books.size());
            }
        } catch (SQLException ex) {
            logger.error("getSomeBooks method exception: " + ex.getSQLState(), ex);
            throw ex;
        }
        return books;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> books;

        Connection con = getConnection();
        books = getAllBooks(con, SQLConstants.SELECT_ALL_BOOKS);
        return books;
    }

    public List<Book> getAllByAuthor(String author) {
        List<Book> books = null;

        Connection con = null;
        try {
            con = getConnection();
            books = getAllBooks(con, "select * from books where author = " + author);
        } catch (SQLException throwables) {
            log.error("Get all by author: " + throwables.getMessage() + throwables.getSQLState() );
            throwables.printStackTrace();
        }
        return books;
    }

    @Override
    public void save(Book book) throws SQLException {
        ResultSet rs = null;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLConstants.SQL_ADD_NEW_BOOK,
                     Statement.RETURN_GENERATED_KEYS)) {

            setBookToPrepStmt(book, pstmt);


            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    book.setId(rs.getLong(1));
                }
            }
            logger.info("book saved successfully book id: "+ book.getId());
        } catch (SQLException throwable) {
            logger.error("Book save() error: " + throwable.getSQLState(), throwable);
            throw throwable;
        } finally {
            close(rs);
        }
    }

    @Override
    public void update(Book book) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLConstants.UPDATE_BOOK)) {
            setBookToPrepStmt(book, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            logger.error("update book method exception: " + throwable.getSQLState(), throwable);
        }
    }

    private void setBookToPrepStmt(Book book, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setLong(3, book.getISBN());
        preparedStatement.setString(4, book.getPublisher());
        preparedStatement.setInt(5, book.getNumber());
        preparedStatement.setString(6, book.getLanguage());
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
            book.setLanguage(rs.getString("language"));
            book.setImage(rs.getString("image"));
        } catch (SQLException throwable) {
            logger.error("update book method exception: " + throwable.getSQLState(), throwable);
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
             PreparedStatement statement = con.prepareStatement(SQLConstants.GET_BOOK_BY_TITLE)) {
            statement.setString(1, title);
            rs = statement.executeQuery();
            while (rs.next()) {
                book = mapBook(rs);
            }

        } catch (SQLException throwable) {
            logger.error("getByTitle book method exception: " + throwable.getSQLState(), throwable);
            throwable.printStackTrace();
        } finally {
            close(rs);
        }
        return book;
    }
}
