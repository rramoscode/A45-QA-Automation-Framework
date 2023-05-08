package PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(xpath = "//a[normalize-space()='rrPlaylist3']")
    private WebElement selectAPlaylist;
    @FindBy(css = "input[name='name']")
    private WebElement playlistNameField;
    @FindBy(xpath = "//a[normalize-space()='rrPlaylist4']")
    WebElement newPlaylistName;


    public void doubleClickSelectPlaylist()  {
        doubleClick(selectAPlaylist);
    }

    public void doubleClickUpdatePlaylistName()  {
        doubleClick(playlistNameField);
        playlistNameField.sendKeys(Keys.DELETE);
        playlistNameField.sendKeys("rrPlaylist4");
        playlistNameField.sendKeys(Keys.ENTER);

    }
    public String newPlaylistNameExist() {
        return findElement(newPlaylistName).getText();
    }

}
