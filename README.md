# BookStoreApplication Automation

This project is a test automation framework for the book store application. The framework is written in Java using Selenium and TestNG

To use these tests java 18 is required. 

Tests are located under src/test/java/tests. 


Currently added tests are:

1. Login and adding book to collection with Selenium
2. Login, adding book to collection and deleting book with Selenium
3. Login and adding book to collection over API
4. Login, adding book to collection and deleting book over API


NB! Every test creates new user for each run to ensure that tests are independent.

For the Selenium UI tests you can change the book for each test in the test itself. Book's are added to enum located in the BookLinks.java class.  To change book thats added you need to change the book enum in the following line:

```java        
        BookLinks.Book book = SPEAKING_JAVASCRIPT;
```

API tests currently ask the available list of books in the store and take the first book from the list. This can later be changed to something more dynamical. 
