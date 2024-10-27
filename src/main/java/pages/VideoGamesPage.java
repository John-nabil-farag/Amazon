package pages;

        import org.openqa.selenium.By;
        import org.openqa.selenium.JavascriptExecutor;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import java.time.Duration;

public class VideoGamesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By videoGamesTitle = By.xpath("//*[@class= 'a-size-base a-color-base apb-browse-refinements-indent-1 a-text-bold']");
    private By freeShippingCheckboxIcon = By.cssSelector("div.a-checkbox.a-checkbox-fancy.apb-browse-refinements-checkbox label[for=\"apb-browse-refinements-checkbox_0\"] i.a-icon.a-icon-checkbox");
//    private By freeShippingCheckboxIcon = By.xpath("//input[@id='apb-browse-refinements-checkbox_0']/following-sibling::i[@class='a-icon a-icon-checkbox']");

    // Constructor
    public VideoGamesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Explicit wait of 10 seconds
    }

    // Method to get the video games page title
    public String getVideoGamesTitle() {
        return driver.findElement(videoGamesTitle).getText();
    }

    // Method to set the free shipping filter
    public FreeShippingPage setFreeShipping() {
        // Wait until the free shipping checkbox icon is clickable
        WebElement freeShipping = wait.until(ExpectedConditions.visibilityOfElementLocated(freeShippingCheckboxIcon));
        wait.until(ExpectedConditions.elementToBeClickable(freeShippingCheckboxIcon));

        // Scroll to the element if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", freeShipping);
        // Click on the free shipping checkbox
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", freeShipping);

        // Return the FreeShippingPage object
        return new FreeShippingPage(driver);
    }
}
