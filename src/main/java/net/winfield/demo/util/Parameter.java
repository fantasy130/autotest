package net.winfield.demo.util;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Parameter {

    private WebDriver driver;

    private String url;

    private String timeout;

    private String locatorType;

    private String locatorExpr;

    private String keyword;

    private String searchKey;

    private String inputValue;

    private String inputRandomValue;

    public void setInputRandomValue(String inputRandomValue) {
        this.inputRandomValue = inputRandomValue;
    }

    public String getInputRandomValue() {
        return String.valueOf(System.currentTimeMillis());
    }

    public String getInputValue() {
        return inputValue;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getTimeout() {
        return timeout;
    }

    public String getLocatorType() {
        return locatorType;
    }

    public String getLocatorExpr() {
        return locatorExpr;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    public void setLocatorExpr(String locatorExpr) {
        this.locatorExpr = locatorExpr;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }
}
