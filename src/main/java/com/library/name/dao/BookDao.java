package com.library.name.dao;

import com.library.name.entity.Book;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDao implements Dao<Book> {
    private static BookDao bookDao;
    private static final Logger logger = Logger.getLogger(BookDao.class);

    /**
     * @return instance of BookDao class
     * */
    public static synchronized BookDao getInstance() {
        if (bookDao == null) {
            bookDao = new BookDao();
        }
        return bookDao;
    }

    private BookDao() {
        // hello everyone
    }

    /**
     * @param id id of book in table books
     * @return book object or null if such record not exists
     * */
    @Override
    public Book get(long id) {
        ResultSet rs = null;
        Book book = null;

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM books where id=?")) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                book = mapBook(rs);
            }
        } catch (SQLException throwable) {
            logger.error("get book method exception: " + throwable.getSQLState());
        } finally {
            close(rs);
        }
        return book;
    }

    /**
     * method for pagination
     * @param start number of begining record
     * @param orderParam we order by this parameter (table column)
     * @return few books from table books
     * */
    public List<Book> getSomeBooks(int start, int numberOfBooks, String orderParam) throws SQLException {
        Connection con = getConnection();
        List<Book> books;
        books = getAllBooks(con, "select * from books where number > 0 order by + " + orderParam + " limit " + (start - 1) * numberOfBooks + "," + numberOfBooks);
        return books;
    }
    /**
     * @param con connection to DB
     * @param query method executes this query
     * @return books by query
     * */
    private static List<Book> getAllBooks(Connection con, String query) throws SQLException {
        List<Book> books = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                books.add(mapBook(rs));
            }
            if (!books.isEmpty()) {
                logger.info("getAllBooks() get successfully number of books: " + books.size());
            }
        } catch (SQLException ex) {
            logger.error("getSomeBooks method exception: " + ex.getSQLState(), ex);
            throw ex;
        }
        return books;
    }
    /**
     * @return all books from table books
     * */
    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> books;

        Connection con = getConnection();
        books = getAllBooks(con, SQLConstants.SELECT_ALL_BOOKS);
        return books;
    }

    /**
     * @param author search from table by this param
     * @return a list of books where books.author = author
     * */
    public List<Book> getAllByAuthor(String author) {
        List<Book> books = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("select * from books where author = ?")
        ) {
            preparedStatement.setString(1, author);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(mapBook(resultSet));
                    log.debug(books);
                }
            }
        } catch (SQLException throwables) {
            log.error("Get all by author: " + throwables.getMessage() + throwables.getSQLState());
            throwables.printStackTrace();
        }
        return books;
    }
    /**
     * increment book number by id when librarian return user`s book
     * */
    public void incrementNumberBook(long id) {
        Book book = get(id);
        int i = book.getNumber() + 1;
        book.setNumber(i);
        update(book);
    }
    /**
     * decrement book number by id when user order book
     * @throws SQLException if book.number < 1
     * */
    public void decrementNumberBook(long id) throws SQLException {
        Book book = get(id);
        int i = book.getNumber() - 1;
        if (i > 0) {
            book.setNumber(book.getNumber() - 1);
            update(book);
        } else throw new SQLException("no books available");
    }
    /**
     * save book in BD
     * @throws SQLException if book can`t added by some reason
     * */
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
            logger.info("book saved successfully book id: " + book.getId());
        } catch (SQLException throwable) {
            logger.error("Book save() error: " + throwable.getSQLState(), throwable);
            throw throwable;
        } finally {
            close(rs);
        }
    }
    /**
     * updates book by its id in DB
     * * */
    @Override
    public void update(Book book) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE books SET title = ?, author= ?, ISBN= ?, publisher= ?, publishingDate=?," +
                             " number= ?, language= ?, image=?, description_ua=?, description_en=? WHERE id = ?;")) {
            log.debug("updateBook: IMAGE" + book.getImage());
            int k = setBookToPrepStmt(book, preparedStatement);

            preparedStatement.setLong(k, book.getId());

            preparedStatement.executeUpdate();
            log.debug("updateBook: IMAGE" + book.getImage());
            log.debug("updateBook: DESCRIPTIONS" + book.getDescriptionUa() + "  " + book.getDescriptionEn());
        } catch (SQLException throwable) {
            logger.error("update book method exception: " + throwable.getSQLState(), throwable);
        }
    }

    private int setBookToPrepStmt(Book book, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;
        preparedStatement.setString(k++, book.getTitle());
        preparedStatement.setString(k++, book.getAuthor());
        preparedStatement.setLong(k++, book.getISBN());
        preparedStatement.setString(k++, book.getPublisher());
        preparedStatement.setDate(k++, book.getPublishingDate());
        preparedStatement.setInt(k++, book.getNumber());
        preparedStatement.setString(k++, book.getLanguage());
        preparedStatement.setString(k++, book.getImage());
        preparedStatement.setString(k++, book.getDescriptionUa());
        preparedStatement.setString(k++, book.getDescriptionEn());
        return k;
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
            book.setDescriptionUa(rs.getString("description_ua"));
            book.setDescriptionEn(rs.getString("description_en"));
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
    /**
     * get book by its title in DB
     * @param title book title
     * */
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
