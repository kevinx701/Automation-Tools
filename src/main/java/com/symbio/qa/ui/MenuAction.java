package com.symbio.qa.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.symbio.qa.beans.DriverBeans;
import com.symbio.qa.drivers.Drivers;
import com.symbio.qa.util.MyProperties;

public class MenuAction {
    private MenuBar menuBar;

    public MenuAction(MenuBar menuBar) {
        this.menuBar = menuBar;
        projectMenu();
        launchBrowser();
    }

    private void projectMenu() {
        menuBar.getBrowserMI().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new BrowserSelectPage();
            }

        });
        menuBar.getExistMI().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (DriverBeans.getIEDriver() != null)
                    DriverBeans.getIEDriver().close();
                if (DriverBeans.getFirefoxDriver() != null)
                    DriverBeans.getFirefoxDriver().close();
                if (DriverBeans.getChromeDriver() != null)
                    DriverBeans.getChromeDriver().close();
                menuBar.jFrame.dispose();
            }
        });
        
        menuBar.getURL().addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                String url = JOptionPane.showInputDialog("Please input mark for test 1: ");
                MyProperties myProperties = null;
                try {
                    myProperties = new MyProperties("src/main/resources/properties/project.properties");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myProperties.setPropertiy("url", url);
                try {
                    myProperties.saveProperties();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        });
    }

    private void launchBrowser() {
        menuBar.getLaunchMI().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                MyProperties myProperties = null;
                try {
                    myProperties = new MyProperties("src/main/resources/properties/project.properties");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String url = myProperties.getPropertiy("url");
                if (Boolean.valueOf(myProperties.getPropertiy("IE"))) {
                    DriverBeans.setIEDriver(new Drivers(url).ieDriver());
                } else {
                    DriverBeans.setIEDriver(null);
                }

                if (Boolean.valueOf(myProperties.getPropertiy("FireFox"))) {
                    DriverBeans.setFirefoxDriver(new Drivers(url).fireFoxDriver());
                } else {
                    DriverBeans.setFirefoxDriver(null);
                }
                
                if (Boolean.valueOf(myProperties.getPropertiy("Chrome"))) {
                    DriverBeans.setChromeDriver(new Drivers(url).chromeDriver());
                }else{
                    DriverBeans.setChromeDriver(null);
                }
            }
        });
        menuBar.getSearch().addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               if (DriverBeans.getIEDriver()!=null){
                   WebElement search = DriverBeans.getIEDriver().findElement(By.cssSelector("#sb_form_q"));
                   search.click();
                   search.clear();
                   search.sendKeys("symbio");
                   DriverBeans.getIEDriver().findElement(By.cssSelector("#sb_form_go")).click();
               }
                
               if (DriverBeans.getChromeDriver()!=null){
                   WebElement search = DriverBeans.getChromeDriver().findElement(By.cssSelector("#sb_form_q"));
                   search.click();
                   search.clear();
                   search.sendKeys("skillsoft");
                   DriverBeans.getChromeDriver().findElement(By.cssSelector("#sb_form_go")).click();
               }
               
               if (DriverBeans.getFirefoxDriver()!=null){
                   WebElement search = DriverBeans.getFirefoxDriver().findElement(By.cssSelector("#sb_form_q"));
                   search.click();
                   search.clear();
                   search.sendKeys("skillsoft");
                   DriverBeans.getFirefoxDriver().findElement(By.cssSelector("#sb_form_go")).click();
               }
            }
            
        });
        
    }

    public static void main(String[] args) {

    }

}
