package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class FreeShippingTest extends BaseTests{

    @Test
    public void videoGames() throws InterruptedException {
        SignInPage signInPage = homePage.clickOnSignIn();
        signInPage.setEmail_mobile("1282175133");
        PasswordPage passwordPage = signInPage.clickOnContinue();
        passwordPage.setPassword("P@ssw0rd");
        HomePage homePage1 = passwordPage.clickOnSignInButton();
        VideoGamesPage videoGamesPage = homePage1.openVideoGames();
        FreeShippingPage freeShippingPage = videoGamesPage.setFreeShipping();
        NewConditionPage newConditionPage = freeShippingPage.newCondition();
    }
}
