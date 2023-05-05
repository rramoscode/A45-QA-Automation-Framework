package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By selectAPlaylist = By.xpath("//a[normalize-space()='rrPlaylist3']");
    By playlistNameField = By.cssSelector("input[name='name']");



    public void doubleClickSelectPlaylist()  {
        doubleClick(selectAPlaylist);
    }

    public void doubleClickUpdatePlaylistName()  {
        doubleClick(playlistNameField);
        findElement(playlistNameField).sendKeys(Keys.DELETE);
        findElement(playlistNameField).sendKeys("rrPlaylist4");
        findElement(playlistNameField).sendKeys(Keys.ENTER);

    }

    public boolean newPlaylistNameExist(String newPlaylistName) {
        By newPlaylist = By.xpath("//a[normalize-space()='rrPlaylist4']");
        return findElement(newPlaylist).isDisplayed();
    }

}
