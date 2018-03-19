package net.winfield.demo.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class PageAction {

    private String noUseField;
    
    public void openBrowser(Parameter parameter) throws InterruptedException {
        parameter.getDriver().manage().window().maximize();
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
    }

    public void closeBrowser(Parameter parameter) {
        parameter.getDriver().quit();
    }

    public void visitURL(Parameter parameter) throws InterruptedException {
        parameter.getDriver().get("https://" + parameter.getUrl());
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
    }

    public void assertTitle(Parameter parameter) throws InterruptedException {
        Assert.assertTrue(parameter.getDriver().getTitle().contains(parameter.getKeyword()));
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
    }

    public void assertPageSource(Parameter parameter) throws InterruptedException {
        Assert.assertTrue(parameter.getDriver().getPageSource().contains(parameter.getKeyword()));
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
    }

    public void search(Parameter parameter) throws Exception {
        WebElement webElement = fineElementByLocator(parameter);
        webElement.sendKeys(parameter.getSearchKey());
        webElement.sendKeys(Keys.ENTER);
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
    }

    public void click(Parameter parameter) throws Exception {
        fineElementByLocator(parameter).click();
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
    }

    public void input(Parameter parameter) throws Exception {
        fineElementByLocator(parameter).sendKeys(parameter.getInputRandomValue());
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
    }

    public void inputRandom(Parameter parameter) throws Exception {
        fineElementByLocator(parameter).sendKeys(parameter.getInputRandomValue());
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
    }

    public void selectRadio(Parameter parameter) throws Exception {
        fineElementByLocator(parameter).sendKeys(Keys.SPACE);
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
    }

    public void switchWindow(Parameter parameter) throws InterruptedException {
        Thread.sleep(Integer.parseInt(parameter.getTimeout()));
        String currentWindow = parameter.getDriver().getWindowHandle();
        Set<String> windows = parameter.getDriver().getWindowHandles();
        List<String> netWin = windows.stream().filter(w -> !w.equals(currentWindow)).collect(toList());
        parameter.getDriver().switchTo().window(netWin.get(0));
    }

    private WebElement fineElementByLocator(Parameter parameter) throws Exception {
        WebElement webElement;
        if ("xpath".equals(parameter.getLocatorType())) {
            webElement = ObjectMap.getElement(parameter.getDriver(), By.xpath(parameter.getLocatorExpr()));
        } else if ("class".equals(parameter.getLocatorType())) {
            webElement = ObjectMap.getElement(parameter.getDriver(), By.className(parameter.getLocatorExpr()));
        } else {
            throw new Exception("Locator type is invalid!");
        }
        return webElement;
    }
}
