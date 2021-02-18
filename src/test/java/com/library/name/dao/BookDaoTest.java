package com.library.name.dao;

import com.library.name.entity.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao bookDao = BookDao.getInstance();
    Book book = new Book();

    @Test
    public void get() {
        Book book = null;
        book = bookDao.get(1);
        Assert.assertNotNull(book);
    }

    @Test
    public void getSomeBooks() throws SQLException {
        List<Book> list = new ArrayList<>();
        list = bookDao.getSomeBooks(1, 10, "id");
        Assert.assertEquals(10, list.size() );
        Assert.assertTrue(list.get(0).getId() < list.get(1).getId());
    }

    @Test
    public void getAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        books = bookDao.getAll();
        Assert.assertTrue(books.size() > 0);
    }

    @Test
    public void getAllByAuthor() {
        List<Book> books;
        books = bookDao.getAllByAuthor("J. Rouling");
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

        book = bookDao.getByTitle("TEST1");
        Assert.assertTrue(book.getId()>0);
    }

    @Test
    public void update() {
        book = bookDao.getByTitle("TEST1");

        book.setNumber(5000);
        bookDao.update(book);

        Assert.assertEquals(5000, book.getNumber());
    }

    @Test
    public void delete() {
    }

    @Test
    public void getByTitle() {
        Book book;
        book = bookDao.getByTitle("test book");
        Assert.assertNotNull(book);
    }
}