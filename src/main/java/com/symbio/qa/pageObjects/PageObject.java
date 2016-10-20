package com.symbio.qa.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    public WebDriver driver;

    public PageObject() {

    }

    public WebElement getElement(String css) {
        return driver.findElement(By.cssSelector(css));
    }

    public WebElement getElement(String css, int count) {
        return getElements(css).get(count);
    }

    public List<WebElement> getElements(String css) {
        return driver.findElements(By.cssSelector(css));
    }

    public String getWinHandle() {
        return driver.getWindowHandle();
    }

    public String getText(String css) {
        return getElement(css).getText();
    }

    public String getText(WebElement webElement) {
        return webElement.getText();
    }

    /*
     * Button methods
     */
    public void click(WebElement webElement) {
        webElement.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void click(String css) {
        click(driver.findElement(By.cssSelector(css)));
    }

    /*
     * editor
     */
    public void editor(WebElement webElement, String value) {
        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    public void editor(String css, String value) {
        WebElement webElement = getElement(css);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    /*
     * dropdown
     */
    public void dropdown(WebElement webElement, String value) {
        new Select(webElement).selectByVisibleText(value);
    }

    public void dropdown(String dropdownCss, String listCss, String value) {
        dropdown(getElement(dropdownCss), getElements(listCss), value);
    }

    public void dropdown(WebElement webElement, List<WebElement> dropDownElements, String value) {
        webElement.click();
        waitElementVisble(dropDownElements.get(0));
        for (WebElement element : dropDownElements)
            if (element.getText().equals(value)) {
                element.click();
                break;
            }
    }

    /*
     * trees, list button, checkbox, radiobutton
     */
    public void checkbox(String css){
        getElement(css);
    }
    /*
     * container(table)
     */
    public void container(){
        
    }
    
    /*
     * Hover
     */
    public void hover(WebElement webElement) {
        if (isElementVisible(webElement)) {
            waitElementVisble(webElement);
        }
        Actions action = new Actions(driver);
        action.moveToElement(webElement).build().perform();
    }

    public boolean isElementVisible(final By locator) {

        boolean flag = false;
        try {
            if (driver.findElement(locator).isDisplayed())
                flag = true;
        } catch (NoSuchElementException e) {
            return false;
        }
        return flag;
    }

    public boolean isElementVisible(final WebElement locator) {
        boolean flag = false;
        try {
            if (locator.isDisplayed())
                flag = true;
        } catch (NoSuchElementException e) {
            return false;
        }
        return flag;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ExpectedCondition<WebElement> waitElementVisble(final WebElement expctedElement) {

        ExpectedCondition<?> elementIsVisible = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                expctedElement.isDisplayed();
                return Boolean.valueOf(true);
            }
        };
        Wait waitForElement = new WebDriverWait(this.driver, 60);
        waitForElement.until(elementIsVisible);
        return (ExpectedCondition<WebElement>) elementIsVisible;
    }

    public static void main(String[] args) {

    }
}
