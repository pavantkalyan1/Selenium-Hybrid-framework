package com.Automation.Framework;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    public void ElementTobevisible(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
        
    public void JSexecutor(WebElement element3){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();", element3);
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

   @FindBy (xpath = "//a[contains(text(),'Place Order')]")
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
    JSexecutor(Placeorder);
    //ElementTobeInvisible();
    //ElementTobeClickable(Placeorder);
    //Placeorder.click();
    //actions(Placeorder);
   }

   @FindBy (xpath = "//tr[@class= 'ng-star-inserted']")
   WebElement OriginalorderId;

   public String getorderId(){
    String s = OriginalorderId.getText().trim();
    // eliminate pipes then trim any leftover spaces
    return s.replace("|", "").trim();
   }

   @FindBy (xpath = "//button[contains(text(),' ORDERS')]")
   WebElement OrdersPage;

   @FindBy (xpath = "//th[@scope='row']")
   List<WebElement> actualorderids;

   @FindBy (xpath = "//button[contains(text(),'View')]")
   List<WebElement> viewbutton;


   public boolean orderspage(String orderId){
    JSexecutor(OrdersPage);
    ElementTobevisible(actualorderids);
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    // // wait until the table rows appear
    // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[@scope='row']")));
    // List<WebElement> ids = driver.findElements(By.xpath("//th[@scope='row']"));
    // List<WebElement> views = driver.findElements(By.xpath("//button[contains(text(),'View')]") );
    // System.out.println("Looking for order id '" + orderId + "' among " + ids.size() + " entries");
    for(int i=0; i<actualorderids.size(); i++){
        String idText = actualorderids.get(i).getText().trim().replace("|", "").trim();
        System.out.println("  row " + i + " has id '" + idText + "'");
        if(idText.equalsIgnoreCase(orderId)){
            WebElement vb = viewbutton.get(i);
            
            ElementTobeInvisible();
            ElementTobeClickable(vb);
            try {
                vb.click();
            } catch (ElementClickInterceptedException e) {
                
                JSexecutor(vb);
            }
            System.out.println("clicked view for index " + i);
            return true;
        }
    }
    return false;
   }

   @FindBy (css = ".col-text")
   WebElement finalOrder;

   public String ordersummaryorderid(){
    return finalOrder.getText().trim();
    
   }
}
