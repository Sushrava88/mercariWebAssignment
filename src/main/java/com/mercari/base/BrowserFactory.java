package com.mercari.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BrowserFactory {
    public static WebDriver createChromeDriver() {
        return new ChromeDriver();
    }

}


