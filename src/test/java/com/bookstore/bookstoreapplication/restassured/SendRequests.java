package com.bookstore.bookstoreapplication.restassured;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SendRequests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendRequests.class);



    public static <T> T sendPostRequest(String url, Object requestBody, Class<T> responseClass) {
        LOGGER.info("Sending POST request to {} with body: {}", url, requestBody);

        RestAssured.baseURI = url;

        Response response = given()
                .filter(new RequestLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)))
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();

        LOGGER.info("Received response with status code {} and body: {}", response.getStatusCode(), response.getBody().asString());
        return response.as(responseClass);
    }

    public static <T> T sendPostRequest(String url, Object requestBody, Class<T> responseClass, String apiKey) {
        LOGGER.info("Sending POST request to {} with body: {}", url, requestBody);

        RestAssured.baseURI = url;

        Response response = given()
                .filter(new RequestLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .body(requestBody)
                .post();

        LOGGER.info("Received response with status code {} and body: {}", response.getStatusCode(), response.getBody().asString());
        return response.as(responseClass);
    }

    public static <T> T sendDeleteRequest(String url, Object requestBody, Class<T> responseClass, String apiKey) {
        LOGGER.info("Sending DELETE request to {} with body: {}", url, requestBody);

        RestAssured.defaultParser = Parser.JSON;
        RestAssured.baseURI = url;

        Response response = given()
                .filter(new RequestLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .body(requestBody)
                .delete();

        String responseBody = response.getBody().asString();
        if (responseBody.isEmpty()) {
            LOGGER.info("Response body is empty");
            return null;
        } else {
            LOGGER.info("Received response with status code {} and body: {}", response.getStatusCode(), responseBody);

        }
        return response.as(responseClass);
    }

    public static <T> T sendGetRequest(String url, Class<T> responseClass) {
        LOGGER.info("Sending GET request to {}", url);

        RestAssured.baseURI = url;

        RequestSpecification request = given()
                .filter(new RequestLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)));

        Response response = request.get();

        LOGGER.info("Received response with status code {} and body: {}", response.getStatusCode(), response.getBody().asString());
        return response.as(responseClass);
    }

    public static <T> T sendGetRequest(String url, Class<T> responseClass, String apiKey) {
        LOGGER.info("Sending GET request to {} with authorization", url);

        RestAssured.baseURI = url;

        RequestSpecification request = given()
                .filter(new RequestLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, new RestAssuredSlf4jPrintStream(LOGGER)))
                .header("Authorization", "Bearer " + apiKey);

        Response response = request.get();

        LOGGER.info("Received response with status code {} and body: {}", response.getStatusCode(), response.getBody().asString());
        return response.as(responseClass);
    }

    private static class RestAssuredSlf4jPrintStream extends PrintStream {

        private final Logger logger;

        RestAssuredSlf4jPrintStream(Logger logger) {
            super(System.out);
            this.logger = logger;
        }

        @Override
        public void println(String x) {
            logger.debug(x);
        }
    }
}