package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.time.Duration;

public class LoginStepDefinitions {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUpBrowser () {
//     Added ChromeOptions argument below to fix websocket error
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
// instance - initializes the wait variable with a new WebDriverWait object that will wait up to 10 seconds for the expected condition to be met.
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @After
    public void closeBrowser () {
        driver.quit();
    }

        @Given("I open the login page")
        public void openLoginpage() {
         driver.get("https://bbb.testpro.io/");
        }
       @When("I enter a email address {string}")
        public void emailAddressField(String email) {
           WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
           emailField.sendKeys(email);
        };
        @And("I enter a password {string}")
        public void passwordField (String password) {
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='password']")));
            passwordField.sendKeys(password);
        };
        @And("I click submit")
        public void clickSubmit() {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']")));
            loginButton.click();
        };
        @Then("I am logged in")
        public void loginComplete() {
            WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[class='avatar']")));
            Assert.assertTrue(avatarIcon.isDisplayed());
        };
    }

