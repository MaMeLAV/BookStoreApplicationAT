package com.bookstore.bookstoreapplication.restassured.Books;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostBooksRequest {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("collectionOfIsbns")
    private List<Isbn> collectionOfIsbns;

    public PostBooksRequest(String userId, List<Isbn> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Isbn> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<Isbn> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public static class Isbn {
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
            return "Isbn{" +
                    "isbn='" + isbn + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PostBooksRequest{" +
                "userId='" + userId + '\'' +
                ", collectionOfIsbns=" + collectionOfIsbns +
                '}';
    }
}