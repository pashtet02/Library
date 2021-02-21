package com.library.name.dao;

import com.library.name.entity.Review;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class ReviewDaoTest {
    ReviewDao reviewDao = ReviewDao.getInstance();
    Review review = new Review();

    @Before
    public void setUp(){
        review = reviewDao.get(21);
    }

    @Test
    public void getAllByUserId() {
        List<Review> reviews;
        reviews = reviewDao.getAllByUserId(review.getUserId());
        Assert.assertTrue(reviews.size() > 0);
        Assert.assertEquals(49L, reviews.get(0).getUserId());
    }

    @Test
    public void get() {
        Review getTest;
        getTest = reviewDao.get(review.getId());
        Assert.assertNotNull(getTest.getUserComment());
        Assert.assertTrue(getTest.getUserId() > 0);
        Assert.assertTrue(getTest.getBookId() > 0);
        Assert.assertTrue(getTest.getMark() > 0);
    }

    @Test
    public void getAll() {
        List<Review> reviews;
        reviews = reviewDao.getAll();
        Assert.assertTrue(reviews.size() > 0);
    }

    @Test
    public void save() throws SQLException {
        review.setId(-5);
        long millis = System.currentTimeMillis();
        Timestamp ts = new Timestamp(millis);
        review.setDate(ts);
        review.setUserId(32);
        review.setBookId(132);
        review.setMark(5);
        review.setUserComment("sdfsdgdsdgsdg");
        review.setBookTitle("TestBook");
        reviewDao.save(review);

        Assert.assertTrue(review.getId()>0);
        Assert.assertNotNull(review.getBookTitle());
        reviewDao.delete(review);
    }

    @Test
    public void update() {
        Random rand = new Random();
        int randNum = rand.nextInt(5);
        review.setMark(randNum);
        reviewDao.update(review);
        Assert.assertEquals(randNum, review.getMark());
        review.setMark(5);
        reviewDao.update(review);
        Assert.assertEquals(5, review.getMark());
    }

    @Test
    public void delete() throws SQLException {
        review.setId(-5);
        long millis = System.currentTimeMillis();
        Timestamp ts = new Timestamp(millis);
        review.setDate(ts);
        review.setUserId(32);
        review.setBookId(132);
        review.setMark(5);
        review.setUserComment("abcabc");
        review.setUsername("im stupid a LOT");
        review.setBookTitle("TestBook");
        reviewDao.save(review);

        int expected = reviewDao.getAll().size() -1;
        reviewDao.delete(review);
        Assert.assertEquals(expected, reviewDao.getAll().size());
    }

    @Test
    public void getAllByBookId() {
        List<Review> reviews;
        reviews = reviewDao.getAllByBookId(review.getBookId());
        boolean res = false;
        for (Review review: reviews) {
           res = review.getBookId() == review.getBookId();
        }
        Assert.assertTrue(res);
    }
    @After
    public void cleanUp() {
        review = null;
    }
}