import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTests extends BaseTest {
@Test
    public static void successfulLoginTest() {

////      Added ChromeOptions argument below to fix websocket error
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("--remote-allow-origins=*");
//
//        WebDriver driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        String url = "https://bbb.testpro.io/";
//        driver.get(url);
//
//        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
//        emailField.sendKeys("randy.ramos@testpro.io");
//
//
//        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
//        passwordField.sendKeys("te$t$tudent");
//
//
//        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
//        loginButton.click();
//// expected Result


    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterEmail("randy.ramos@testpro.io");
    loginPage.enterPassword("te$t$tudent");
    loginPage.clickSubmit();

    WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
    Assert.assertTrue(avatarIcon.isDisplayed());
        driver.quit();

    }
    @Test
    public static void LoginEmptyEmailPasswordTest() {

//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://bbb.testpro.io/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();
    }
}
