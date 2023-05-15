package com.bookstore.bookstoreapplication.restassured.Books;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookResponse {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("message")
    private String message;

    public DeleteBookResponse(String userId, String isbn, String message) {
        this.userId = userId;
        this.isbn = isbn;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DeleteBookResponse{" +
                "userId='" + userId + '\'' +
                ", isbn='" + isbn + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
