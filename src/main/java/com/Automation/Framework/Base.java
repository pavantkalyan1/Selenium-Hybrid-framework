package com.Automation.Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.support.ui.Wait;


import com.Automation.Framework.utils.Utilities.ConfigReader;


import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Base {

    protected WebDriver driver;  //1.driver declaration
    protected ConfigReader config; 
    
   @BeforeClass
    public void Setup(){
    //2.Browser Initialisation
        config = new ConfigReader();  // 3.Initialising configReader class
        String browser = config.getbrowser();

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else {
            throw new RuntimeException("Browser initialisation issue"+ " " +browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(config.getURL());
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
    }


    @AfterClass
    public void teardown()
    {
        if(driver!= null){
            driver.quit();
        }
    }

    protected Logger log = LogManager.getLogger(Base.class); //5.Logging setup
}
