import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{
    @Test
    public void renamePlaylist()  {

        String newPlaylistName = "rrPlaylist4";

        login("demo@class.com" , "te$t$tudent");
        doubleClickSelectPlaylist();
        doubleClickUpdatePlaylistName();
        Assert.assertTrue(newPlaylistNameExist());

    }

    private void doubleClickSelectPlaylist()  {
        WebElement selectAPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='rrPlaylist3']")));
        actions.doubleClick(selectAPlaylist).perform();
    }
    private void doubleClickUpdatePlaylistName()  {
        WebElement playlistNameField = driver.findElement(By.cssSelector("input[name='name']"));
        actions.doubleClick(playlistNameField).perform();
        playlistNameField.sendKeys(Keys.DELETE);
        playlistNameField.sendKeys("rrPlaylist4");
        playlistNameField.sendKeys(Keys.ENTER);

    }
    private boolean newPlaylistNameExist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='rrPlaylist4']")));
        WebElement playlistElement = driver.findElement(By.xpath("//a[normalize-space()='rrPlaylist4']"));
        return playlistElement.isDisplayed();
    }

}
