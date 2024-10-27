package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NewConditionPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By dropDown = By.id("s-result-sort-select");

    /////////////////////////////////////////

    // Constructor initializing WebDriver and WebDriverWait
    public NewConditionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // 10 seconds timeout

    }

    public void selectHighToLow() {
        WebElement sortByDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(dropDown));
        wait.until(ExpectedConditions.elementToBeClickable(dropDown));
        // Scroll to the element if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sortByDropDown);
        Select select = new Select(driver.findElement(dropDown));
        select.selectByVisibleText("Price: High to Low");
    }

    // Method to find and add products below 15K EGP to the cart
    public void productsBelow_15K() throws InterruptedException {
        boolean hasNextPage = true;
        boolean productAdded = false;
        int priceThreshold = 15000;

        while (hasNextPage) {
            // Get list of all products on the page
            List<WebElement> products = driver.findElements(By.cssSelector(".s-main-slot .s-result-item"));

            // Track if any product is added to cart (below 15k EGP)
            productAdded = false;

            // Loop through each product
            for (WebElement product : products) {
                try {
                    // Locate price element, checking if it exists
                    List<WebElement> priceElements = product.findElements(By.cssSelector(".a-price-whole"));

                    WebElement nextElement = wait.until(ExpectedConditions.visibilityOf(product));
                    wait.until(ExpectedConditions.elementToBeClickable(product));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextElement);

                    // Skip if price is not found for the product
                    if (priceElements.isEmpty()) {
                        System.out.println("No price available for this product, skipping...");
                        continue;
                    }

                    WebElement priceElement = priceElements.get(0);  // Get the first price element
                    String priceText = priceElement.getText().replace(",", "");  // Remove commas

                    // Parse the price as double
                    double price = Double.parseDouble(priceText);

                    // Check if the product price is below the threshold
                    if (price < priceThreshold) {
                        WebElement addToCartButton = product.findElement(By.cssSelector(".a-button-inner input[type='submit']"));
                        addToCartButton.click();
                        Thread.sleep(2000);  // Wait for cart confirmation

                        System.out.println("Added product to cart with price: " + priceText + " EGP");
                        productAdded = true;
                    }
                } catch (Exception e) {
                    // Handle cases where price or add to cart button is not found
                    System.out.println("Error processing product: " + e.getMessage());
                }
            }

            // If no products below 15K EGP were added on this page, try to navigate to the next page
            if (!productAdded) {
                try {
                    WebElement nextPageButton = driver.findElement(By.cssSelector(".s-pagination-next"));
                    if (nextPageButton.isEnabled()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextPageButton);
                        nextPageButton.click();
                        Thread.sleep(3000);  // Wait for the next page to load
                    } else {
                        hasNextPage = false;  // No more pages to navigate
                    }
                } catch (Exception e) {
                    // If there's no "Next" button, break the loop
                    System.out.println("No more pages available: " + e.getMessage());
                    hasNextPage = false;
                }
            }
        }
    }
}