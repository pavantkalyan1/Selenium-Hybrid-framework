package com.Automation.Framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class Tests extends Base{

    Page page;
    

    @Test (priority = 0)
    public void LoginApplication(){
        page = new Page(driver);
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
    }
}
