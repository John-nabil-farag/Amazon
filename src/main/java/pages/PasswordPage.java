package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordPage {

    private WebDriver driver;
    private By password = By.id("ap_password");
    private By signInButton = By.id("signInSubmit");

    /////////////////////////////////////////////////////

    public PasswordPage(WebDriver driver){
        this.driver = driver;
    }

    public void setPassword(String password){
        driver.findElement(this.password).sendKeys(password);
    }

    public HomePage clickOnSignInButton(){
        driver.findElement(this.signInButton).click();
        return new HomePage(driver);
    }
}
