package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    private WebDriver driver;
    private By email_mobile = By.id("ap_email");
    private By continueButton = By.id("continue");

    ////////////////////////////////////////////////////////////////////

    public SignInPage(WebDriver driver){
        this.driver = driver;
    }

    public void setEmail_mobile(String email_mobile){
        driver.findElement(this.email_mobile).sendKeys(email_mobile);
    }

    public PasswordPage clickOnContinue(){
        driver.findElement(this.continueButton).click();
        return new PasswordPage(driver);
    }

}
