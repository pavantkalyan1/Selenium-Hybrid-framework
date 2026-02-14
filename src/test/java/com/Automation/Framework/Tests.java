package com.Automation.Framework;

import org.testng.annotations.Test;

public class Tests extends Base {

    @Test
    public void openSite(){
        // `driver` is initialized in Base.Setup()
        System.out.println("Page title: " + driver.getTitle());
    }
}
