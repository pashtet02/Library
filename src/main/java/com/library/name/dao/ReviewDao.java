package com.library.name.dao;

import com.library.name.entity.Review;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ReviewDao implements Dao<Review> {
    private static final Logger log = Logger.getLogger(ReviewDao.class);
    private static ReviewDao reviewDao;

    public static synchronized ReviewDao getInstance() {
        if (reviewDao == null) {
            reviewDao = new ReviewDao();
        }
        return reviewDao;
    }

    private ReviewDao() {
        // hello everyone
    }

    public List<Review> getAllByUserId(long userId) {
        List<Review> reviews = null;

        Connection con;
        try {
            con = getConnection();
            reviews = getAllReviews(con, "select * from reviews where user_id = " + userId + " order by creationDate DESC");
        } catch (SQLException throwables) {
            log.error(throwables.getMessage() + throwables.getSQLState());
            throwables.printStackTrace();
        }
        return reviews;
    }

    @Override
    public Review get(long id) {
        ResultSet rs = null;
        Review review = null;

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM reviews where id=?")) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                review = mapReview(rs);
            }
        } catch (SQLException throwable) {
            log.error("get review method exception: " + throwable.getSQLState());
        } finally {
            close(rs);
        }
        return review;
    }

    @Override
    public List<Review> getAll() {
        List<Review> reviews = null;

        Connection con;
        try {
            con = getConnection();
            reviews = getAllReviews(con, "select * from reviews order by creationDate DESC;");
        } catch (SQLException throwables) {
            log.error(throwables.getMessage() + throwables.getSQLState());
            throwables.printStackTrace();
        }
        return reviews;
    }

    @Override
    public void save(Review review) throws SQLException {
        ResultSet rs = null;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "insert into reviews (user_id, book_id, mark, user_comment, username, bookTitle, creationDate) values (?, ?, ?, ?, ?,?,?);",
                     Statement.RETURN_GENERATED_KEYS)) {

            setReviewToPrepStatement(review, pstmt);

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    review.setId(rs.getLong(1));
                }
            }
            log.info("review saved successfully book id: " + review.getId());
        } catch (SQLException throwable) {
            log.error("Review save() error: " + throwable.getSQLState(), throwable);
            throw throwable;
        } finally {
            close(rs);
        }
    }

    @Override
    public void update(Review review) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE reviews SET mark = ?, user_comment= ? WHERE id = ?;")) {

            preparedStatement.setInt(1, review.getMark());
            preparedStatement.setString(2, review.getUserComment());
            preparedStatement.setLong(3, review.getId());

            preparedStatement.executeUpdate();
            log.debug("updated UserComment" + review.getUserComment());
        } catch (SQLException throwable) {
            log.error("update book method exception: " + throwable.getSQLState(), throwable);
        }
    }

    @Override
    public void delete(Review review) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("delete from reviews where id= ?;")) {
            preparedStatement.setLong(1, review.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            java.util.logging.Logger logger = java.util.logging.Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, throwable.getSQLState(), throwable);
        }
    }

    private void setReviewToPrepStatement(Review review, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;
        preparedStatement.setLong(k++, review.getUserId());
        preparedStatement.setLong(k++, review.getBookId());
        preparedStatement.setInt(k++, review.getMark());
        preparedStatement.setString(k++, review.getUserComment());
        preparedStatement.setString(k++, review.getUsername());
        preparedStatement.setString(k++, review.getBookTitle());
        preparedStatement.setTimestamp(k,review.getDate());
    }

    private static Review mapReview(ResultSet rs) {
        Review review = new Review();
        try {
            review.setId(rs.getLong("id"));
            review.setUserId(rs.getLong("user_id"));
            review.setBookId(rs.getLong("book_id"));
            review.setBookTitle(rs.getString("bookTitle"));
            review.setUsername(rs.getString("username"));
            review.setDate(rs.getTimestamp("creationDate"));

            review.setMark(rs.getInt("mark"));
            review.setUserComment(rs.getString("user_comment"));
        } catch (SQLException throwable) {
            log.error("update book method exception: " + throwable.getSQLState(), throwable);
        }
        return review;
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

    private static List<Review> getAllReviews(Connection con, String query) throws SQLException {
        List<Review> reviews = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                reviews.add(mapReview(rs));
            }

            if (!reviews.isEmpty()) {
                log.debug("getAllReviews() get successfully number of books: " + reviews.size());
            }
        } catch (SQLException ex) {
            log.error("getReviewsAll method exception: " + ex.getSQLState(), ex);
            throw ex;
        }
        return reviews;
    }

    public List<Review> getAllByBookId(Long bookId) {
        List<Review> reviews = null;

        Connection con;
        try {
            con = getConnection();
            reviews = getAllReviews(con, "select * from reviews where book_id = " + bookId);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage() + throwables.getSQLState());
            throwables.printStackTrace();
        }
        return reviews;
    }
}
