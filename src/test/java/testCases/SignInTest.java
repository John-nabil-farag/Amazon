package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PasswordPage;
import pages.SignInPage;

public class SignInTest  extends BaseTests {

    @Test
    public void signIn(){
        SignInPage signInPage = homePage.clickOnSignIn();
        signInPage.setEmail_mobile("1282175133");
        PasswordPage passwordPage = signInPage.clickOnContinue();
    }
}
