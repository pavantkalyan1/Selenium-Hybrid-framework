package com.Automation.Framework;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.interactions.Actions;
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
   
   //webdriver wait 
    public void ElementTobeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void ElementTobeInvisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
    }
        
     //Actions class
    public void actions(WebElement btnelement) {
        Actions act = new Actions(driver);
        act.moveToElement(btnelement).click().perform();
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

   @FindBy (xpath = "//button[contains(text(), ' Cart ')]")
   WebElement cartsbutton;

   public void cartpage(){
    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
    ElementTobeInvisible();
    ElementTobeClickable(cartsbutton);
    cartsbutton.click();
   }

   @FindBy (xpath = "//button[contains(text(),'Checkout')]")
   WebElement checkout;

   public void checkoutbutton(){
    actions(checkout);
   }

   @FindBy (xpath = "//div[contains(text(),'CVV Code ')]/following-sibling::input")
   WebElement Cvvlo;
   
   @FindBy (xpath = "//div[contains(text(),'Name on Card ')]/following-sibling::input")
   WebElement Nameoncard;

   @FindBy (xpath = "//div[contains(text(),'Apply Coupon ')]/following-sibling::input")
   WebElement Couponcode;

   @FindBy (xpath = "//input[@placeholder= 'Select Country']")
   WebElement Countrycode;

   @FindBy (css = ".btn-primary")
   WebElement Applycoupon;

   @FindBy (css = ".ta-item")
   List<WebElement> Allcountries;

   @FindBy (css = ".action__submit ")
   WebElement Placeorder;

   public void allcountry(String country){
    for (WebElement Expectedcountry : Allcountries) {
        if(Expectedcountry.getText().trim().equalsIgnoreCase(country)){
            Expectedcountry.click();
            break;
        }
    }
   }

   public void Enterbilllingdetails(String CVV, String Name, String Coupon, String country){
    Cvvlo.sendKeys(CVV);
    Nameoncard.sendKeys(Name);
    Couponcode.sendKeys(Coupon);
    Countrycode.sendKeys(country);
    allcountry(country);
    Applycoupon.click();
    ElementTobeInvisible();
    //ElementTobeClickable(Placeorder);
    //Placeorder.click();
    actions(Placeorder);
   }
}
