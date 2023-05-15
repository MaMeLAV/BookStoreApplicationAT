package com.bookstore.bookstoreapplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookLinks {

    public enum Book {
        GIT_POCKET_GUIDE("9781449325862"),
        YOU_DONT_KNOW_JS("9781491904244"),
        LEARNING_JAVA_DESIGN_PATTERNS("9781449331818"),
        DESIGNING_EVOLVABLE_WEB_WITH("9781449337711"),
        SPEAKING_JAVASCRIPT("9781449365035"),
        PROGRAMMING_JAVA_APPLICATIONS("9781491950296"),
        ELOQUENT_JAVA_SECOND_EDITION("9781593275846"),
        UNDERSTANDING_ECMA("9781593277574"),
        UNDERSTANDING_ECMA2("9781593277574");

        private final String bookId;
        private WebElement link;

        @FindBy(xpath = "//a[@href='/profile?book=9781449365035']")
        public WebElement linkSpeakingJava;


        Book(String bookId) {
            this.bookId = bookId;
        }


        public WebElement getBookStoreLink(WebDriver driver) {
            link = driver.findElement(By.xpath("//a[@href='/books?book="+ bookId +"']"));
            return link;
        }

        public WebElement getProfileLink(WebDriver driver) {
            link = driver.findElement(By.xpath("//a[@href='/profile?book="+ bookId +"']"));
            return link;
        }
    }
}
