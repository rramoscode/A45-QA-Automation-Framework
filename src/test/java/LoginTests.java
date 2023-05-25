import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTests extends BaseTest {
@Test
    public void successfulLoginTest() {

    login("randy.ramos@testpro.io" , "te$t$tudent");
// expected Result
    Assert.assertTrue(avatarIcon());


    }
    @Test
    public void LoginEmptyEmailPasswordTest() {

        login("","");

//    Verify user stays on login page using Assert method
        String url = "https://bbb.testpro.io/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

    }

    @Test
    public void registrationNavigation() {

    openloginUrl();

//    find and click element registration link
        registrationLink();

//    Verify redirect to Registration page using Assert method
        String expectedUrl = "https://bbb.testpro.io/registration.php";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

    }
}
