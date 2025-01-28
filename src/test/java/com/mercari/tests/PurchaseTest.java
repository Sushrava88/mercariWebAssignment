package com.mercari.tests;

import com.mercari.pages.CartPage;
import com.mercari.pages.CheckoutPage;
import com.mercari.pages.LoginPage;
import com.mercari.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class PurchaseTest {
    WebDriver driver;
    LoginPage loginPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @Given("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        driver = new ChromeDriver();
        driver.get(CommonUtils.getProperty("url"));
        driver.manage().window().maximize();


        // Initialize Pages
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Read credentials from configuration
        String username = CommonUtils.getProperty("username");
        String password = CommonUtils.getProperty("password");

        loginPage.login(username, password);
    }

    @When("the user adds the first and second items to the cart")
    public void theUserAddsItemsToCart() {
        cartPage.addFirstItemToCartFromDetailsPage();
        cartPage.addSecondItemToCartFromDetailsPage();
        // Verify the cart contains 2 items
        int cartItemCount = cartPage.getCartItemCount();
        Assert.assertEquals(cartItemCount, 2, "Cart item count should be 2 after adding two items.");
        System.out.println(cartItemCount);
    }

    @And("the user proceeds to checkout and enters address details")
    public void theUserProceedsToCheckout() {
        cartPage.goToCart();
        checkoutPage.startCheckout();
        String firstName = CommonUtils.getProperty("firstName");
        String lastName = CommonUtils.getProperty("lastName");
        String postalCode = CommonUtils.getProperty("postalCode");

        checkoutPage.enterAddressDetails(firstName, lastName, postalCode);
        checkoutPage.clickContinueButton();
    }

    @Then("the user should see the checkout overview and complete the purchase successfully")
    public void theUserShouldCompletePurchaseSuccessfully() {
        // Validate total price on the Checkout Overview screen
        String totalPrice = checkoutPage.getTotalPrice();
        Assert.assertTrue(totalPrice.contains("43.18"), "Total price should match expected value.");

        // Complete the purchase
        checkoutPage.completePurchase();

        // Validate confirmation message
        String confirmationMessage = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(confirmationMessage, "Thank you for your order!",
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!");

        // Log success and quit driver
        System.out.println("Order placed successfully: " + confirmationMessage);
        driver.quit();
    }
}

