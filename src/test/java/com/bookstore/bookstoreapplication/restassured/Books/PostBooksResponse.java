package com.bookstore.bookstoreapplication.restassured.Books;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostBooksResponse {
    @JsonProperty("books")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "PostBooksResponse{" +
                "books=" + books +
                '}';
    }

    public static class Book {
        @JsonProperty("isbn")
        private String isbn;

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "isbn='" + isbn + '\'' +
                    '}';
        }
    }
}