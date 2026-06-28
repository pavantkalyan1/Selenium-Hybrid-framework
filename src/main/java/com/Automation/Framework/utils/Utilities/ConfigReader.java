package com.Automation.Framework.utils.Utilities;

import java.util.Properties;
import java.io.FileInputStream;

public class ConfigReader {
    
    private Properties prop;

    public ConfigReader(){

        try{
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/main/Resources/Config.properties");
            prop.load(fis);
        }
        catch(Exception e){
            throw new RuntimeException("Failed to load config file!", e);
        }
    }

    public String getbrowser(){
        return prop.getProperty("browser");
    }

    public String getURL(){
        return prop.getProperty("URL");
    }


}
