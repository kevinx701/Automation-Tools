package com.symbio.qa.beans;

import org.openqa.selenium.WebDriver;

public final class DriverBeans {
    
    private static WebDriver ieDriver;
    private static WebDriver firefoxDriver;
    private static WebDriver chromeDriver;
    private static WebDriver htmlunitDriver;

    private DriverBeans(){
        
    }
    
    public static void setIEDriver(WebDriver driver){
        ieDriver = driver;
    }
    
    public static WebDriver getIEDriver(){
        return ieDriver;
    }
    
    public static void setFirefoxDriver(WebDriver driver){
        firefoxDriver = driver;
    }
    
    public static WebDriver getFirefoxDriver(){
        return firefoxDriver;
    }
    
    public static void setChromeDriver(WebDriver driver){
        chromeDriver = driver;
    }
    
    public static WebDriver getChromeDriver(){
        return chromeDriver;
    }
    
    public static void setHtmlunitDriver(WebDriver driver){
        htmlunitDriver = driver;
    }
    
    public static WebDriver getHtmlunitDriver(){
        return htmlunitDriver;
    }
}
