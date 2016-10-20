package com.symbio.qa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MyProperties {

    public Properties prop;
    public String propertiesFile;
    String Interval;
    String keyword;
    String productions;
    String[] products;
    Logger logger;
    FileInputStream in = null;

    public MyProperties(String propertiesFile) throws IOException {
        this.propertiesFile = propertiesFile;
        FileInputStream in = new FileInputStream(propertiesFile);
        prop = new Properties();
        prop.load(in);
    }

    public Properties getProperties() {
        return prop;
    }

    public String getPropertiy(String key) {
        return prop.getProperty(key);
    }

    public void setPropertiy(String key, String value) {
        prop.setProperty(key, value);
    }

    public void saveProperties() throws IOException {
        FileOutputStream fos = null;
        fos = new FileOutputStream(propertiesFile);
        prop.store(fos, "Updated");
        prop.clear();
        fos.close();
    }

    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
