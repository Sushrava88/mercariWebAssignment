package com.mercari.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class BasePage {
    protected WebDriver driver;
    private Logger logger = Logger.getLogger(BasePage.class.getName());

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Wait for a specific element to be visible on the page
    public void waitForPageToLoad(WebElement element, String pageName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Transitioned to " + pageName + " successfully.");
        } catch (Exception e) {
            logger.severe("Failed to load " + pageName + ": " + e.getMessage());
            throw new RuntimeException("Page not loaded: " + pageName);
        }
    }
    protected void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Log screen transitions for better debugging
    protected void logScreenTransition(String message) {
        System.out.println(message);
    }
}
