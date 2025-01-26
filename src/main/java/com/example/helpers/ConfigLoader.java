package com.example.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader instance;
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config/properties")){
            properties.load(fis);
        } catch (IOException e){
            throw new RuntimeException("Failed to load config properties: " + e.getMessage(), e);
        }
    }

    public static ConfigLoader getInstance(){
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
