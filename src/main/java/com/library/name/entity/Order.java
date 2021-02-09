package com.library.name.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Order {
    private Long userId;
    private Long bookId;
    private String username;
    private  String userMail;

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String comment) {
        this.userComment = comment;
    }

    private String userComment;

    public String getLibrarianComment() {
        return librarianComment;
    }

    public void setLibrarianComment(String librarianComment) {
        this.librarianComment = librarianComment;
    }

    private String librarianComment;
    public int getBooksNumber() {
        return booksNumber;
    }

    public void setBooksNumber(int booksNumber) {
        this.booksNumber = booksNumber;
    }

    private int booksNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

    public void setUserSecondName(String userSecondName) {
        this.userSecondName = userSecondName;
    }

    public double getUserFine() {
        return userFine;
    }

    public void setUserFine(double userFine) {
        this.userFine = userFine;
    }

    private String userFirstName;
    private String userSecondName;
    private double userFine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }


    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    private String bookTitle;
    private String bookAuthor;

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    private Timestamp startDate = new Timestamp(System.currentTimeMillis());
    private Date returnDate;
    private String status;

    public Order(){
        startDate = new Timestamp(System.currentTimeMillis());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                ", username='" + username + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userComment='" + userComment + '\'' +
                ", librarianComment='" + librarianComment + '\'' +
                ", booksNumber=" + booksNumber +
                ", userFirstName='" + userFirstName + '\'' +
                ", userSecondName='" + userSecondName + '\'' +
                ", userFine=" + userFine +
                ", id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", startDate=" + startDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}
