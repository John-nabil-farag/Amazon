package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By accountListsArrow = By.xpath("//*[@class= 'nav-line-2 ']");
//    private By accountListsArrow = By.id("nav-link-accountList");
    private By shortenedName = By.id("nav-your-amazon-text");
    private By allMenu = By.className("hm-icon-label");
    private By seeAll = By.xpath("//*[@id='hmenu-content']/ul[1]/li[14]/a[1]/i");
    private By videoGames = By.xpath("//*[@id='hmenu-content']/ul[1]/ul/li[11]/a/div");
    private By allVideoGames = By.xpath("//*[contains(text(),'All Video Games')]");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Explicit wait of 10 seconds
    }

    // Method to click on Sign In
    public SignInPage clickOnSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(accountListsArrow)).click();
        return new SignInPage(driver);
    }

    // Method to get the shortened name text
    public String getShortenedName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shortenedName)).getText();
    }

    // Method to navigate to Video Games page
    public VideoGamesPage openVideoGames() {
        // Click on the All Menu
        wait.until(ExpectedConditions.elementToBeClickable(allMenu)).click();

        // Scroll and click on "See All"
        WebElement seeAllLink = wait.until(ExpectedConditions.visibilityOfElementLocated(seeAll));
        scrollToElement(seeAllLink);
        seeAllLink.click();

        // Scroll and click on "Video Games"
        WebElement videoGamesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(videoGames));
        scrollToElement(videoGamesLink);
        videoGamesLink.click();

        // Scroll and click on "All Video Games"
        WebElement allVideoGamesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(allVideoGames));
        wait.until(ExpectedConditions.elementToBeClickable(allVideoGames));
        scrollToElement(allVideoGamesLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allVideoGamesLink);
        return new VideoGamesPage(driver);
    }

    // Helper method to scroll to an element
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
