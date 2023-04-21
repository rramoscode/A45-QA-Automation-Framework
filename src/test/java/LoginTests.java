import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {
@Test
    public void successfulLoginTest() {

        openloginUrl();
        enterEmail("demo@class.com");
        enterPassword("te$t$tudent");
        clickSubmit();

// expected Result
        WebElement avatarIcon = driver.findElement(By.cssSelector("[class='avatar']"));
        Assert.assertTrue(avatarIcon.isDisplayed());

    }
    @Test
    public void loginInvalidEmailPasswordTest() {
    openloginUrl();
    enterEmail("invalid@gmail.com");
    enterPassword("invalidPassword");
    clickSubmit();

// verify we are still on login page
    Assert.assertEquals(driver.getCurrentUrl(), "https://bbb.testpro.io/");

    }
    @Test
    public static void loginNotExistingEmailTest() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://bbb.testpro.io/";
        driver.get(url);

        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        emailField.sendKeys("notExistingEmail@class.com");

        WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
        passwordField.sendKeys("te$t$tudent");

        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();

    }

    @Test
    public static void loginNotEmptyPasswordTest(){
        // Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://bbb.testpro.io/";
        driver.get(url);

        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        emailField.sendKeys("demo@class.com");

        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), url);
           System.out.println("this is the current URL: " + url);

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

        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();
    }
}
