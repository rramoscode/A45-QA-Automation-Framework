import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Homework22 extends BaseTest{

    @Test
    public void renamePlaylist()  {

        String newPlaylistName = "rrPlaylist4";

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login();
        homePage.doubleClickSelectPlaylist();
        homePage.doubleClickUpdatePlaylistName();

        Assert.assertTrue(homePage.newPlaylistNameExist(newPlaylistName));

    }
}
