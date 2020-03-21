package com.wuyue.domain;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Book
 * @description
 * @date 2020/3/21 19:30
 */
public class Book {
    private String bookName;
    private double price;
    private String author;
    private Address address;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", address=" + address +
                '}';
    }
}














