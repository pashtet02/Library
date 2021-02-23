package com.library.name.entity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

public class BookTest {
    Book book;
    @Before
    public void setUp(){
        book = new Book();
    }
    @Test
    public void getersTest(){
        book.setNumber(5);
        book.setISBN(1235765874125L);
        book.setAuthor("test");
        book.setTitle("test");
        book.setLanguage("test");
        book.setPublisher("test");
        book.setPublishingDate(Date.valueOf("1922-12-12"));
        book.setDescriptionEn("test");
        book.setDescriptionUa("test");
        book.setId(5);
        Assert.assertEquals("test", book.getDescriptionUa());
        Assert.assertEquals("test", book.getLanguage());
        Assert.assertEquals("test", book.getDescriptionEn());
        Assert.assertEquals("test", book.getTitle());
        Assert.assertEquals("test", book.getAuthor());
        Assert.assertEquals(5, book.getNumber());
        Assert.assertEquals(1235765874125L, book.getISBN());
        Long expected = Long.parseLong("5");
        Assert.assertEquals(expected, book.getId());
    }

    @Test
    public void toStringTest(){
        Book book = new Book();
        book.setImage("test");
        Assert.assertTrue(book.toString().length() > 0);
    }
    @After
    public void cleanUp(){
        book = null;
    }
}
