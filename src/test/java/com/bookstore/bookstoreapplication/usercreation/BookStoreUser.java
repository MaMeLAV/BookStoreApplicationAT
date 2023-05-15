package com.bookstore.bookstoreapplication.usercreation;

public class BookStoreUser {


    private String firstName;
    private String lastName;
    private String username;
    private String password;




    public static BookStoreUser newUser(){
        BookStoreUser newUser = new BookStoreUser(generateRandomString(),generateRandomString(),generateRandomString(),generateRandomString()+"1aA2@");
        return newUser;
    }

    public static String generateRandomString(){
        String randomString = java.util.UUID.randomUUID().toString().substring(0, 10);
        return randomString;
    }

    public BookStoreUser(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    // Getter methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setter methods
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
