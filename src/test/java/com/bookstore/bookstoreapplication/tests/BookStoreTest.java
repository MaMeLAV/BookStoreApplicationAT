package com.bookstore.bookstoreapplication.tests;

import com.bookstore.bookstoreapplication.BookLinks;
import com.bookstore.bookstoreapplication.restassured.Books.*;
import com.bookstore.bookstoreapplication.restassured.GenerateToken.GenerateTokenRequest;
import com.bookstore.bookstoreapplication.restassured.GenerateToken.GenerateTokenResponse;
import com.bookstore.bookstoreapplication.restassured.SendRequests;
import com.bookstore.bookstoreapplication.restassured.account.GetUserResponse;
import com.bookstore.bookstoreapplication.restassured.account.UserRequest;
import com.bookstore.bookstoreapplication.restassured.account.UserResponse;
import com.bookstore.bookstoreapplication.usercreation.BookStoreUser;
import com.bookstore.bookstoreapplication.MainPage;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;

import java.util.List;

import static com.bookstore.bookstoreapplication.BookLinks.Book.*;
import static com.bookstore.bookstoreapplication.restassured.Books.PostBooksRequest.*;
import static com.bookstore.bookstoreapplication.restassured.SendRequests.*;


public class BookStoreTest {

    private WebDriver driver;
    private MainPage mainPage;
    private JavascriptExecutor js;
    private static final Logger LOGGER = LoggerFactory.getLogger(SendRequests.class);


    @Test(groups = {"UI"})
    @Description("Test case 1 - This test creates new user over API and uses Selenium to add book to collection")
    public void addBookToCollection() {
        BookLinks.Book book = SPEAKING_JAVASCRIPT;

        LOGGER.info("Create new user");
        BookStoreUser user = BookStoreUser.newUser();
        UserRequest newAccount = new UserRequest(user.getUsername(), user.getPassword());
        UserResponse accountResponse = sendPostRequest("https://demoqa.com/Account/v1/User", newAccount, UserResponse.class);

        LOGGER.info("Open book store application with user and log in");
        openBookStoreAndLogin(user);

        LOGGER.info("Open book: " + book.name());
        openBook(book);

        LOGGER.info("Add book to collection");
        addToCollection();

        LOGGER.info("Check that added book is in collection");
        checkBookExistsInCollection(book);

    }

    @Test(groups = {"UI"})
    @Description("Test case 2 - This test creates new user over API and uses Selenium to add and remove book to collection")
    public void addAndRemoveBookFromCollection() {
        BookLinks.Book book = ELOQUENT_JAVA_SECOND_EDITION;

        LOGGER.info("Create new user");
        BookStoreUser user = BookStoreUser.newUser();
        UserRequest newAccount = new UserRequest(user.getUsername(), user.getPassword());
        UserResponse accountResponse = sendPostRequest("https://demoqa.com/Account/v1/User", newAccount, UserResponse.class);

        LOGGER.info("Open book store application with user and log in");
        openBookStoreAndLogin(user);

        LOGGER.info("Open book: " + book.name());
        openBook(book);

        LOGGER.info("Add book to collection");
        addToCollection();

        LOGGER.info("Check that added book is in collection");
        checkBookExistsInCollection(book);

        LOGGER.info("Delete book from the user");
        deleteFirstBook();

        LOGGER.info("Check that the user collection is empty");
        checkEmptyCollection();
    }

    @Test(groups = {"API"})
    @Description("Test case 3 - This test creates new user and adds book to collection over API")
    public void addBookToCollectionOverAPI() {
        LOGGER.info("Create new user");
        BookStoreUser user = BookStoreUser.newUser();
        UserRequest newAccount = new UserRequest(user.getUsername(), user.getPassword());
        UserResponse accountResponse = sendPostRequest("https://demoqa.com/Account/v1/User", newAccount, UserResponse.class);

        LOGGER.info("Create authentication token for the user");
        GenerateTokenRequest request = new GenerateTokenRequest(user.getUsername(), user.getPassword());
        GenerateTokenResponse tokenResponse = sendPostRequest("https://demoqa.com/Account/v1/GenerateToken", request, GenerateTokenResponse.class);
        String authToken = tokenResponse.getToken();

        LOGGER.info("Get all the books from the bookStore");
        GetBooksResponse booksResponse = sendGetRequest("https://demoqa.com/BookStore/v1/Books", GetBooksResponse.class);

        LOGGER.info("Select the fist book and add it to user collection");
        Isbn isbn = new Isbn();
        isbn.setIsbn(booksResponse.getBooks().get(0).getIsbn());
        PostBooksRequest booksRequest = new PostBooksRequest(accountResponse.getUserId(), List.of(isbn));
        PostBooksResponse postBooksResponse = sendPostRequest("https://demoqa.com/BookStore/v1/Books", booksRequest, PostBooksResponse.class, authToken);
        Assert.assertEquals(postBooksResponse.getBooks().get(0).getIsbn(),booksResponse.getBooks().get(0).getIsbn(),"Added book and saved book does not match");

        LOGGER.info("Check if the book is successfully saved to the collection");
        GetUserResponse getUserResponse = sendGetRequest("https://demoqa.com/Account/v1/User/"+ accountResponse.getUserId(), GetUserResponse.class,authToken);
        Assert.assertFalse(getUserResponse.getBooks().isEmpty(), "User should have at least 1 book in the collection");
    }

    @Test(groups = {"API"})
    @Description("Test case 4 - This test creates new user and adds book to collection over API")
    public void addBookToCollectionAndRemovalOverAPI() {
        LOGGER.info("Create new user");
        BookStoreUser user = BookStoreUser.newUser();
        UserRequest newAccount = new UserRequest(user.getUsername(), user.getPassword());
        UserResponse accountResponse = sendPostRequest("https://demoqa.com/Account/v1/User", newAccount, UserResponse.class);

        LOGGER.info("Create authentication token for the user");
        GenerateTokenRequest request = new GenerateTokenRequest(user.getUsername(), user.getPassword());
        GenerateTokenResponse tokenResponse = sendPostRequest("https://demoqa.com/Account/v1/GenerateToken", request, GenerateTokenResponse.class);
        String authToken = tokenResponse.getToken();

        LOGGER.info("Get all the books from the bookStore");
        GetBooksResponse booksResponse = sendGetRequest("https://demoqa.com/BookStore/v1/Books", GetBooksResponse.class);

        LOGGER.info("Select the fist book and add it to user collection");
        Isbn isbn = new Isbn();
        isbn.setIsbn(booksResponse.getBooks().get(0).getIsbn());
        PostBooksRequest booksRequest = new PostBooksRequest(accountResponse.getUserId(), List.of(isbn));
        PostBooksResponse postBooksResponse = sendPostRequest("https://demoqa.com/BookStore/v1/Books", booksRequest, PostBooksResponse.class, authToken);
        Assert.assertEquals(postBooksResponse.getBooks().get(0).getIsbn(),booksResponse.getBooks().get(0).getIsbn(),"Added book and saved book does not match");

        LOGGER.info("Check if the book is successfully saved to the collection");
        GetUserResponse getUserResponse = sendGetRequest("https://demoqa.com/Account/v1/User/"+ accountResponse.getUserId(), GetUserResponse.class,authToken);
        Assert.assertFalse(getUserResponse.getBooks().isEmpty(), "User should have at least 1 book in the collection");

        LOGGER.info("Delete book from the user collection");
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(booksResponse.getBooks().get(0).getIsbn(), accountResponse.getUserId());
        sendDeleteRequest("https://demoqa.com/BookStore/v1/Book", deleteBookRequest, DeleteBookResponse.class, authToken);

        LOGGER.info("Check that user does not have any remaining books in the collection");
        GetUserResponse getUserResponse2 = sendGetRequest("https://demoqa.com/Account/v1/User/"+ accountResponse.getUserId(), GetUserResponse.class,tokenResponse.getToken());
        Assert.assertTrue(getUserResponse2.getBooks().isEmpty(),"Deletion of the book was unsuccessful and the User has still books in the collection");
    }


    @BeforeMethod(groups = {"UI"})
    public void setUp(ITestResult result) {
        // Check if the test method has the "UI" group
        boolean isUIGroup = Arrays.asList(result.getMethod().getGroups()).contains("UI");

        if (isUIGroup) {
            ChromeOptions options = new ChromeOptions();
            // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://demoqa.com/");
            mainPage = new MainPage(driver);
            js = (JavascriptExecutor) driver;
        }
    }

    @AfterMethod(groups = {"UI"})
    public void tearDown(ITestResult result) {
        boolean isUIGroup = Arrays.asList(result.getMethod().getGroups()).contains("UI");
        if (isUIGroup) {
            driver.quit();
        }
    }


    public static void login(MainPage page, String username, String password) {
        page.loginButton.click();
        page.inputUserName.sendKeys(username);
        page.inputPassword.sendKeys(password);
        page.loginButton.click();
    }

    public void scrollDown(Number pixels) {
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }


    public void openBookStoreAndLogin(BookStoreUser user) {
        scrollDown(600);
        mainPage.bookStoreApplication.click();
        login(mainPage, user.getUsername(), user.getPassword());
        System.out.println("User: " + user.getUsername() + " is logged in");
    }

    public void addToCollection() {
        scrollDown(600);
        mainPage.buttonAddNewRecord.isDisplayed();
        mainPage.buttonAddNewRecord.click();
        String alertMessage = acceptAlertBox();
        Assert.assertEquals(alertMessage, "Book added to your collection.");
    }

    public String acceptAlertBox() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        String message = alert.getText();
        System.out.println("Alert Text is: " + message);

        alert.accept();
        return message;
    }


    public void openBook(BookLinks.Book book) {

        js.executeScript("arguments[0].scrollIntoView();", book.getBookStoreLink(driver));
        book.getBookStoreLink(driver).click();
        System.out.println("Opened book: " + book.name());
    }

    public void checkBookExistsInCollection(BookLinks.Book book) {
        mainPage.goToProfile.click();
        String message = book.getProfileLink(driver).getText();
        System.out.println("Book in collection is: " + message);
    }

    public void waitUntilElementIsClickable(Integer waitInSeconds, WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void deleteFirstBook() {

        mainPage.deleteBook.click();
        waitUntilElementIsClickable(10, mainPage.deleteBookOk);
        mainPage.deleteBookOk.click();
        String alertMessage = acceptAlertBox();
        Assert.assertEquals(alertMessage, "Book deleted.");
    }

    public void checkEmptyCollection() {
        String message = mainPage.noRowsFound.getText();
        Assert.assertEquals(message, "No rows found");
        System.out.println(message + "in collection");

    }

}
