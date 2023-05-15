package com.bookstore.bookstoreapplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://demoqa.com/
public class MainPage {


    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div[6]")
    public WebElement bookStoreApplication;

    @FindBy(id = "login")
    public WebElement loginButton;

    @FindBy(id = "userName")
    public WebElement inputUserName;

    @FindBy(id = "password")
    public WebElement inputPassword;


    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[9]/div[2]/button")
    public WebElement buttonAddNewRecord;


    @FindBy(xpath = "//*[text() = 'Profile']")
    public WebElement goToProfile;


    @FindBy(id = "delete-record-undefined")
    public WebElement deleteBook;

    @FindBy(id = "closeSmallModal-ok")
    public WebElement deleteBookOk;

    @FindBy(id = "closeSmallModal-cancel")
    public WebElement deleteBookCancel;

    @FindBy(css = "div.rt-noData")
    public WebElement noRowsFound;


    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
