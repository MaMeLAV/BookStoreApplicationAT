package com.bookstore.bookstoreapplication.restassured.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class GetUserResponse {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("books")
    private List<Book> books;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "GetUserResponse{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", books=" + books +
                '}';
    }

    public static class Book {
        @JsonProperty("isbn")
        private String isbn;
        @JsonProperty("title")
        private String title;
        @JsonProperty("subTitle")
        private String subTitle;
        @JsonProperty("author")
        private String author;
        @JsonProperty("publish_date")
        private LocalDateTime publish_date;
        @JsonProperty("publisher")
        private String publisher;
        @JsonProperty("pages")
        private int pages;
        @JsonProperty("description")
        private String description;
        @JsonProperty("website")
        private String website;

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public LocalDateTime getPublishDate() {
            return publish_date;
        }

        public void setPublishDate(LocalDateTime publish_date) {
            this.publish_date = publish_date;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "isbn='" + isbn + '\'' +
                    ", title='" + title + '\'' +
                    ", subTitle='" + subTitle + '\'' +
                    ", author='" + author + '\'' +
                    ", publish_date=" + publish_date +
                    ", publisher='" + publisher + '\'' +
                    ", pages=" + pages +
                    ", description='" + description + '\'' +
                    ", website='" + website + '\'' +
                    '}';
        }
    }
}
