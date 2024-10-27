package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignInPage;

public class HomeTest extends BaseTests{

    @Test
    public void signIn(){
        SignInPage signInPage = homePage.clickOnSignIn();
    }
}
