package com.wuyue;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/21 11:12
 */
public class Book {
    private double price;
    private String bookName;
    private String author;

    public Book(double price, String bookName, String author) {
        this.price = price;
        this.bookName = bookName;
        this.author = author;
    }

    public Book() {
    }

    public double getPrice() {
        return price;
    }

    public Book setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
