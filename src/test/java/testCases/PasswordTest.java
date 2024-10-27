package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PasswordPage;
import pages.SignInPage;

public class PasswordTest extends BaseTests {

    @Test
    public void signIn(){
        SignInPage signInPage = homePage.clickOnSignIn();
        signInPage.setEmail_mobile("1282175133");
        PasswordPage passwordPage = signInPage.clickOnContinue();
        passwordPage.setPassword("P@ssw0rd");
        HomePage homePage1 = passwordPage.clickOnSignInButton();
        Assert.assertEquals(homePage1.getShortenedName(), "test's amazon.eg");
    }
}
