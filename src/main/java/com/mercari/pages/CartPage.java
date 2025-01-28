package com.mercari.pages;

import com.mercari.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {
    WebDriver driver;

    @FindBy(css = ".inventory_item:nth-of-type(1) .inventory_item_name")
    private WebElement firstItemLink; // Link to first item's details page

    @FindBy(css = ".inventory_item:nth-of-type(2) .inventory_item_name")
    private WebElement secondItemLink; // Link to second item's details page

    @FindBy(css = ".btn_inventory")
    private WebElement addToCartButton; // Add to cart button on the item screen

    @FindBy(id = "back-to-products")
    private WebElement backToShowcaseButton; // Back button to return to showcase screen

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge; // Displays the number of items in the cart

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addFirstItemToCartFromDetailsPage() {
        firstItemLink.click(); // Navigate to first item's details page
        waitForElementToBeClickable(addToCartButton);
        addToCartButton.click(); // Add to cart
        logScreenTransition("First Item Added to Cart");
        backToShowcaseButton.click(); // Return to showcase screen
        waitForElementToBeClickable(secondItemLink); // Wait for the second item to be visible
    }

    public void addSecondItemToCartFromDetailsPage() {
        secondItemLink.click(); // Navigate to second item's details page
        waitForElementToBeClickable(addToCartButton);
        addToCartButton.click(); // Add to cart
        logScreenTransition("Second Item Added to Cart");
        backToShowcaseButton.click(); // Return to showcase screen
    }
    public int getCartItemCount() {
        String itemCountText = cartBadge.getText();
        return Integer.parseInt(itemCountText); // Convert the badge text to an integer
    }
    public void goToCart() {
        cartIcon.click();
        waitForPageToLoad(cartIcon, "Cart Page");
    }
}

