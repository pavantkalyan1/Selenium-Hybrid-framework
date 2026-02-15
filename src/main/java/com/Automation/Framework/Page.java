package com.Automation.Framework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Page extends Base{

    protected WebDriver driver;

    public Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    
    //Login page
    @FindBy (id = "userEmail")
    WebElement EmailAddress;

    @FindBy (id = "userPassword")
    WebElement EmailPassword;

    @FindBy (id = "login")
    WebElement loginButton;

    public void Login(String email, String password){
        EmailAddress.sendKeys(email);
        EmailPassword.sendKeys(password);
        loginButton.click();
    }

   public void waitForVisibility(WebElement element, int seconds) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    wait.until(ExpectedConditions.visibilityOf(element));
}
    //Home Page
    @FindBy (css = ".card-body")
    List<WebElement> cardsInShopping; 

    @FindBy (xpath = "(//button[contains(text(),'View')])")
    WebElement ViewButton;

    @FindBy (xpath = "//button[contains(text(),' Add To Cart')]")
    WebElement AddtocartButton;


   public void Productselect(String productname){
        for (WebElement card : cardsInShopping) {
            if(card.findElement(By.tagName("b")).getText().equals(productname)){
                card.findElement(By.xpath(".//button[text()=' Add To Cart']")).click();
                break;
            }
        
        }
   }

   @FindBy (xpath = "//button[@routerlink = './dashboard/cart']")
   WebElement cartsbutton;

   public void cartpage(){
    waitForVisibility(cartsbutton, 20);
    cartsbutton.click();
   }
}