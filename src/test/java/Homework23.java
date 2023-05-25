import PageFactory.HomePage;
import PageFactory.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


    public class Homework23 extends BaseTest {

        @Test
        public void renamePlaylist() {

            LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
            HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);

            loginPage.login();
            homePage.doubleClickSelectPlaylist();
            homePage.doubleClickUpdatePlaylistName();

     }
 }

