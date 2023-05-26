package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }


    private By emailField = By.cssSelector("[type='email']");

    private By passwordField = By.cssSelector("[type='password']");

    private By loginButton = By.cssSelector("[type='submit']");

    public void enterEmail(String email) {
        findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit() {
        findElement(loginButton).click();
    }
    public void login() {

        enterEmail("randy.ramos@testpro.io");
        enterPassword("te$t$tudent");
        clickSubmit();

    }

    public boolean isPageOpened() {
        return findElement(emailField).isDisplayed();
    }
}
