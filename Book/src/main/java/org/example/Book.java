package org.example;

import java.io.Serializable;

public class Book implements Serializable {
    String name;
    String author;
    int price;
    public  static BookBuilder builder(){
        return new BookBuilder();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public static class BookBuilder {
        private String name;
        private String author;
        private int price;
        private BookBuilder() {};
        public BookBuilder name(String name) {
            this.name = name;
            return this;
        }
        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }
        public BookBuilder price(int price) {
            this.price = price;
            return this;
        }
        public Book build() {
            return new Book(name, author, price);
        }
    }
}
