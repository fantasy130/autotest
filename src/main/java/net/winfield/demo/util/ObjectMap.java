package net.winfield.demo.util;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ObjectMap {

    public static WebElement getElement(WebDriver webDriver, final By by) {
        List<WebElement> webElements = new WebDriverWait(webDriver, 30).until(x -> x.findElements(by));
        return webElements.get(0);
    }

    public static List<WebElement> getElements(WebDriver webDriver, final By by) {
        return new WebDriverWait(webDriver, 30).until(x -> x.findElements(by));
    }
}
