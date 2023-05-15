package com.bookstore.bookstoreapplication.restassured.GenerateToken;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.Objects;

public class GenerateTokenResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("expires")
    private OffsetDateTime expires;

    @JsonProperty("status")
    private String status;

    @JsonProperty("result")
    private String result;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OffsetDateTime getExpires() {
        return expires;
    }

    public void setExpires(OffsetDateTime expires) {
        this.expires = expires;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerateTokenResponse that = (GenerateTokenResponse) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(expires, that.expires) &&
                Objects.equals(status, that.status) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expires, status, result);
    }

    @Override
    public String toString() {
        return "GenerateTokenResponse{" +
                "token='" + token + '\'' +
                ", expires=" + expires +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

