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
import java.util.ArrayList;

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

    public void productsBelow_15K() throws InterruptedException {
        boolean hasNextPage = true;
        int priceThreshold = 15000;

        boolean productAdded = false;
        while (hasNextPage) {
            // Get list of all products on the page
            List<WebElement> products = driver.findElements(By.cssSelector(".s-main-slot .s-result-item"));

            // Track if any product is added to cart (below 15k EGP)
            productAdded = false;

            for (WebElement product : products) {
                // Locate price element
                WebElement priceElement = product.findElement(By.className("a-price-whole"));

                WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated((By) product));
                wait.until(ExpectedConditions.elementToBeClickable(product));
                // Scroll to the element if necessary
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);

                String priceText = priceElement.getText().replace(",", "");  // Remove commas for proper parsing
                double price = Double.parseDouble(priceText);

                // If price is below 15,000 EGP, add to cart
                if (price < priceThreshold) {
                    // Find and click the 'Add to Cart' button
                    WebElement addToCartButton = product.findElement(By.cssSelector(".a-button-inner input[type='submit']"));
                    addToCartButton.click();

                    // Wait for cart confirmation (can be improved with WebDriverWait)
                    Thread.sleep(2000);

                    System.out.println("Added product to cart with price: " + priceText + " EGP");
                    productAdded = true;
                }
            }
        }

        // Check if there are no products below 15k on the current page
        if (!productAdded) {
            try {
                // Find the "Next" page button and navigate if it's enabled
                WebElement nextPageButton = driver.findElement(By.cssSelector(".s-pagination-next"));
                WebElement nextPage = wait.until(ExpectedConditions.visibilityOfElementLocated((By) nextPageButton));
                wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
                // Scroll to the element if necessary
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextPage);
                if (nextPageButton.isEnabled()) {
                    nextPageButton.click();
                    Thread.sleep(3000);  // Wait for the next page to load
                } else {
                    hasNextPage = false;  // Exit loop if no next page
                }
            } catch (Exception e) {
                // If no next page button is found, exit the loop
                hasNextPage = false;
                System.out.println("No more pages available: " + e.getMessage());
            }

        }
    }
}
