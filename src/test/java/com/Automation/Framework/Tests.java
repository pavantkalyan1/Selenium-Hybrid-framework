package com.Automation.Framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class Tests extends Base{

    Page page;
    
    @BeforeMethod
    public void initPage() {
        page = new Page(driver);
    }

    @Test (priority = 0)
    public void LoginApplication(){
        page.Login("testing222@gmail.com","Test@123");
        System.out.println("Entered the username/ email address");
    }

    @Test (priority = 1)
    public void ProductSelection(){
        page.Productselect("ZARA COAT 3");
        System.out.println("Added product to cart");
    }

    @Test (priority = 2)
    public void Clickoncart(){
        page.cartpage();
        System.out.println("Clicked on Orders page");
    }

    @Test (priority = 3)
    public void Clickcheckout(){
        page.checkoutbutton();
        System.out.println("Clicked on checkout button");
    }

    @Test (priority = 4)
    public void Enterbilllingdetails(){
        page.Enterbilllingdetails("123","pavan","rahulshettyacademy","india");
        System.out.println("Clicked on Place order");
    }

    String orderId;

    @Test (priority = 5)
    public void OriginalOrderId(){
        orderId = page.getorderId();
        System.out.println("Original Order Id = " + orderId);
    }

    @Test (priority = 6)
    public void Clickonordersandverify(){
        boolean clicked = page.orderspage(orderId);
        assert clicked : "View button was not clicked for order " + orderId;
        System.out.println("Navigated to order details for " + orderId);
    }
    }

