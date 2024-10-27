package testCases;

import org.testng.annotations.Test;
import pages.*;

public class NewConditionTest extends BaseTests{

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
        newConditionPage.selectHighToLow();
        newConditionPage.productsBelow_15K();
    }
}
