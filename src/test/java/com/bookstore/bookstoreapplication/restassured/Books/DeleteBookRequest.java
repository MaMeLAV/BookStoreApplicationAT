package com.bookstore.bookstoreapplication.restassured.Books;


import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookRequest {
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("userId")
    private String userId;

    public DeleteBookRequest(String isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DeleteBookRequest{" +
                "isbn='" + isbn + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
