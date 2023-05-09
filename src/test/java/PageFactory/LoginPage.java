package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }


    @FindBy(css = "[type='email']")
    private WebElement emailField;

    @FindBy(css = "[type='password']")
    private WebElement passwordField;

     @FindBy(css = "[type='submit']")
    private WebElement loginButton;

    public LoginPage enterEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage  clickSubmit() {
        loginButton.click();
        return this;
    }
    public void login() {

        enterEmail("randy.ramos@testpro.io");
        enterPassword("te$t$tudent");
        clickSubmit();

    }

}
