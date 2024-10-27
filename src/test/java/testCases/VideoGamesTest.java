package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PasswordPage;
import pages.SignInPage;
import pages.VideoGamesPage;

public class VideoGamesTest extends BaseTests{

    @Test
    public void videoGames() throws InterruptedException {
        SignInPage signInPage = homePage.clickOnSignIn();
        signInPage.setEmail_mobile("1282175133");
        PasswordPage passwordPage = signInPage.clickOnContinue();
        passwordPage.setPassword("P@ssw0rd");
        HomePage homePage1 = passwordPage.clickOnSignInButton();
        VideoGamesPage videoGamesPage = homePage1.openVideoGames();
        Assert.assertEquals(videoGamesPage.getVideoGamesTitle(), "Video Games");
        videoGamesPage.setFreeShipping();
    }
}
