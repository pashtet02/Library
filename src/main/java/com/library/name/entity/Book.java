package com.library.name.entity;

public class Book extends Entity {
    private long id;
    private String title;
    private String author;
    private long ISBN;
    private String publisher;
    private java.sql.Date publishingDate;
    private int number;
    private String language;
    private String image;

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    private String descriptionUa;
    private String descriptionEn;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public java.sql.Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(java.sql.Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN=" + ISBN +
                ", publisher='" + publisher + '\'' +
                ", publishingDate=" + publishingDate +
                ", number=" + number +
                ", language='" + language + '\'' +
                ", image='" + image + '\'' +
                ", descriptionUa='" + descriptionUa + '\'' + "\n" +
                ", descriptionEn='" + descriptionEn + '\'' + "\n" +
                '}';
    }

    public Book(){
        //hello
    }
}
