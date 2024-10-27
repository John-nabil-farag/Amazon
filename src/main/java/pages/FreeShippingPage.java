//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public class FreeShippingPage {
//    private WebDriver driver;
//
//    /////////////////////////////////////////////////////
//    public FreeShippingPage(WebDriver driver){
//        this.driver = driver;
//    }
//
//    public NewConditionPage newCondition() throws InterruptedException {
////        WebElement newCondition = driver.findElement(By.xpath("//li[@id='p_n_condition-type/28071525031']"));
//        WebElement newCondition = driver.findElement(By.xpath("//*[@id=\"filters\"]/ul[1]/span/span[1]"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newCondition);
//        Thread.sleep(2000);
//        newCondition.click();
//        return new NewConditionPage(driver);
//    }
//}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FreeShippingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for New Condition filter element
    private By newConditionLocator = By.cssSelector("li#p_n_condition-type\\/28071525031 a");

    /////////////////////////////////////////////////////
    // Constructor initializing WebDriver and WebDriverWait
    public FreeShippingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // 10 seconds timeout
    }

    // Method to interact with the "New Condition" filter
    public NewConditionPage newCondition() {
        try {
            // Wait for the "New Condition" element to be visible
            WebElement newCondition = wait.until(ExpectedConditions.visibilityOfElementLocated(newConditionLocator));
            wait.until(ExpectedConditions.elementToBeClickable(newConditionLocator));

            // Scroll to the element if necessary
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newCondition);
            // Click on the free shipping checkbox
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newCondition);

            // Click the element once it is clickable
//            wait.until(ExpectedConditions.elementToBeClickable(newCondition)).click();

            // Returning a new instance of the NewConditionPage
            return new NewConditionPage(driver);

        } catch (Exception e) {
            System.out.println("Error while trying to click 'New Condition': " + e.getMessage());
            return null;  // Consider throwing a custom exception if you want to handle this higher up
        }
    }
}
