package com.Automation.Framework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class Page {

    WebDriver driver;

    public void page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    
    //Login page

    //Home Page
    @FindBy (css = ".card-body")
    WebElement cardsInShopping; 

   
}