package com.library.name.dao;

import com.library.name.entity.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class BookDaoTest {

    BookDao bookDao = BookDao.getInstance();
    Book book = new Book();

    @Before
    public void setUp(){
        book = bookDao.getByTitle("TestBook");
    }

    @Test
    public void get() {
        Book getTest;
        getTest = bookDao.get(book.getId());
        Assert.assertNotNull(getTest.getAuthor());
        Assert.assertNotNull(getTest.getTitle());
    }
    @After
    public void cleanUp() {
        book = null;
    }

    @Test
    public void getSomeBooks() throws SQLException {
        List<Book> list;
        list = bookDao.getSomeBooks(1, 4, "id");
        Assert.assertEquals(4, list.size() );
        Assert.assertTrue(list.get(0).getId() < list.get(1).getId());
    }

    @Test
    public void getAll() throws SQLException {
        List<Book> books;
        books = bookDao.getAll();
        Assert.assertTrue(books.size() > 0);
    }

    @Test
    public void getAllByAuthor() {
        List<Book> books;
        books = bookDao.getAllByAuthor("TestAuthor");
        Assert.assertTrue(books.size() > 0);
    }

    @Test
    public void save() throws SQLException{
        book.setNumber(5);
        book.setISBN(1235765874125L);
        book.setAuthor("TEST12");
        book.setTitle("TEST12");
        book.setLanguage("ua");
        book.setPublisher("asfasfas");
        book.setPublishingDate(Date.valueOf("1922-12-12"));
        book.setDescriptionEn("heheheh");
        book.setId(-5);
        bookDao.save(book);

        book = bookDao.getByTitle("TEST12");
        Assert.assertTrue(book.getId()>0);
        Assert.assertNotNull(book.getTitle());
        bookDao.delete(book);
    }

    @Test
    public void update() {
        Random rand = new Random();
        int randNum = rand.nextInt(50);
        book.setNumber(randNum);
        bookDao.update(book);
        Assert.assertEquals(randNum, book.getNumber());
        book.setNumber(18);
        bookDao.update(book);
    }


    /*@Test
    public void delete() throws SQLException {
        int expected = bookDao.getAll().size() -1;
        bookDao.delete(book);
        Assert.assertEquals(expected, bookDao.getAll().size());
        bookDao.save(book);
    }*/

    @Test
    public void getByTitle() {
        Book book;
        book = bookDao.getByTitle("TestBook");
        Assert.assertNotNull(book.getTitle());
        Assert.assertNotNull(book.getAuthor());
    }

    @Test
    public void incrementNumberBook() {
        bookDao.incrementNumberBook(book.getId());
        long expected = 19L;
        Assert.assertEquals(expected, bookDao.get(book.getId()).getNumber());
        book.setNumber(18);
        bookDao.update(book);
    }

    @Test
    public void decrementNumberBook() throws SQLException {
        bookDao.decrementNumberBook(book.getId());
        long expected = 17L;
        Assert.assertEquals(expected, bookDao.get(book.getId()).getNumber());
        book.setNumber(18);
        bookDao.update(book);
    }

    @Test
    public void getByISBN() {
        Book getISBNTest;
        getISBNTest = bookDao.getByISBN(book.getISBN());
        Assert.assertNotNull(getISBNTest.getAuthor());
        Assert.assertNotNull(getISBNTest.getTitle());
    }
}