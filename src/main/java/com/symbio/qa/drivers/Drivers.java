package com.symbio.qa.drivers;

import java.util.NoSuchElementException;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Drivers {

    public WebDriver driver;
    private WebDriverEventListener webDriverEventListener = new EventListener();
    private String url;

   
    public Drivers(String url) {
        this.url = url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public WebDriver ieDriver() {
        driver = getDriver("ie");
        driver.get(url);
        return driver;
    }

    public WebDriver chromeDriver() {
        driver = getDriver("chrome");
        driver.get(url);
        return driver;
    }

    public WebDriver fireFoxDriver() {
        driver = getDriver("firefox");
        driver.get(url);
        return driver;
    }

    public WebDriver htmlunitDriver() {
        driver = getDriver("htmlunit");
        driver.get(url);
        return driver;
    }

    public WebDriver getDriver(String browserName) {

        if (browserName.equalsIgnoreCase("firefox")) {

            // FirefoxProfile profile = getFFProfile();

            FirefoxProfile profile = new FirefoxProfile();

            // String path;
            // path = PropertyConstant.WINDOWS_DOWNLOAD_FOLDER_PATH;

            profile.setPreference("browser.download.folderList", 2);
            // profile.setPreference("browser.download.dir", path);
            profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                    "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
            profile.setPreference("browser.download.manager.showWhenStarting", false);
            profile.setPreference("browser.download.manager.focusWhenStarting", false);
            profile.setPreference("browser.download.useDownloadDir", true);
            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
            profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
            profile.setPreference("browser.download.manager.closeWhenDone", true);
            profile.setPreference("browser.download.manager.showAlertOnComplete", false);
            profile.setPreference("browser.download.manager.useWindow", false);
            profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
            profile.setPreference("pdfjs.disabled", true);

            driver = new FirefoxDriver(profile);
            driver.manage().window().maximize();
            driver = new EventFiringWebDriver(driver).register(webDriverEventListener);
        } else if (browserName.equalsIgnoreCase("ie")) {

            System.setProperty("webdriver.ie.driver", "src\\main\\resources\\driver\\IEDriverServer.exe");

            driver = new InternetExplorerDriver();
            driver = new EventFiringWebDriver(driver).register(webDriverEventListener);

        } else if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver = new EventFiringWebDriver(driver).register(webDriverEventListener);

        } else if (browserName.equalsIgnoreCase("htmlunit")) {

            driver = new HtmlUnitDriver();
            driver = new EventFiringWebDriver(driver).register(webDriverEventListener);
            driver.get("about:blank");

        }
        if (driver instanceof HtmlUnitDriver)

            ((HtmlUnitDriver) driver).setJavascriptEnabled(true);

        return driver;

    }

    public void waitForPageToLoad(String timeout) {
        try {
            int count = Integer.parseInt(timeout) / 1000;
            String pageLoaded = "";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String status = "return window.document.readyState;";

            do {
                pageLoaded = (String) js.executeScript(status);
                Wait(1);
                count--;
                if (count <= 0) {
                    Reporter.log("timeout for waiting to load page");
                    Reporter.log("timeout for waiting to load page");
                    pageLoaded = "complete";
                    break;
                }
            } while (!pageLoaded.equalsIgnoreCase("complete"));
        } catch (Exception ex) {
            Reporter.log(ex.getMessage());
        }
    }

    public void Wait(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (Exception ex) {
            Reporter.log(ex.getMessage());
        }
    }

    public final void WindowsMaximize() {
        ((JavascriptExecutor) driver).executeScript("if (window.screen)" + "{" + "window.moveTo(0, 0);"
                + "window.resizeTo(window.screen.availWidth,window.screen.availHeight);" + "};");
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
                // }
                // else
                // return Boolean.valueOf(false);

            }
        };

        Wait waitForElement = new WebDriverWait(this.driver, 60);
        waitForElement.until(elementIsVisible);

        return (ExpectedCondition<WebElement>) elementIsVisible;

    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        BasicConfigurator.configure();
        String url = "https://symbio1vsp80ac.qa.skillport.com/skillportfe/login.action";
        WebDriver driver1 = null;
        Drivers driver = new Drivers(url);
        WebDriver driverFireFox = driver.fireFoxDriver();
        WebDriver driverIE = driver.ieDriver();
        WebDriver driverChrome = driver.chromeDriver();
        WebDriver driverHTML = driver.getDriver("htmlunit");

    }

}
