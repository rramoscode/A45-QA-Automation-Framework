import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class Homework16 extends BaseTest{

    @Test
    public static void registrationNavigation() {

//     Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

//    set driver chromedriver to use Chrome browser
        WebDriver driver = new ChromeDriver(options);

//    navigate to URL per test case
        String url = ("https://bbb.testpro.io/");
        driver.get(url);

//    find and click element registration link
        WebElement registrationLink = driver.findElement(By.cssSelector("#hel"));
        registrationLink.click();

//    Verify redirect to Registration page using Assert method
        String registrationUrl = "https://bbb.testpro.io/registration.php";
        Assert.assertEquals(driver.getCurrentUrl(), registrationUrl);

// quit driver
       driver.quit();

    }
}
