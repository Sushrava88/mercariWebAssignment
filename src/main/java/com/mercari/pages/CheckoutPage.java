package com.mercari.pages;

import com.mercari.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    WebDriver driver;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "first-name")
    private WebElement firstNameInput; // Input field for First Name

    @FindBy(id = "last-name")
    private WebElement lastNameInput; // Input field for Last Name

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput; // Input field for Postal Code

    @FindBy(id = "continue")
    private WebElement continueButton; // Button to navigate to the overview page

    @FindBy(css = ".summary_total_label")
    private WebElement totalPriceLabel; // Total price label on the overview page

    @FindBy(id = "finish")
    private WebElement finishButton; // Button to complete the purchase

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void startCheckout() {
        checkoutButton.click();
    }

    public void enterAddressDetails(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
        logScreenTransition("Address Input Screen");
    }

    public void clickContinueButton() {
        continueButton.click();
        waitForPageToLoad(totalPriceLabel, "Checkout Overview Screen");
    }

    public String getTotalPrice() {
        return totalPriceLabel.getText();
    }

    public void completePurchase() {
        finishButton.click();
        waitForPageToLoad(confirmationMessage, "Confirm Purchase Screen");
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }
}
