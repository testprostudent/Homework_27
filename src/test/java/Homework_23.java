import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Homework_23 extends BaseTest{
    @Test
    public void RenamePlaylist(){
        String playlistName="test";
        LoginPage loginPage =new LoginPage(driver);
        HomePage homePage =new HomePage(driver);
        loginPage.login();
        homePage.doubleClickPlaylist();
        homePage.entrePlaylistName(playlistName);
        Assert.assertTrue(homePage.doesPlaylistExit(playlistName));

    }
}
