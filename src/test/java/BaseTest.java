import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
WebDriver driver;
// Test annotation and the helper/reusable methods
    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void setUpBrowser () {

//     Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterMethod
    public void tearDownBrowser () {
        driver.quit();
    }

    public void openloginUrl() {
        driver.get("https://bbb.testpro.io/");
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement loginButton = driver.findElement(By.cssSelector(("[type='submit']")));
        loginButton.click();
    }

    public void searchSong (String songTitleKeyword) throws InterruptedException {
        //Search for a song (choose any song of your choice)
        WebElement searchFeild = driver.findElement(By.cssSelector("[name='q']"));
        searchFeild.sendKeys(songTitleKeyword);
        Thread.sleep(2000);
    }

    public void clickViewAllBtn () throws InterruptedException {
        // click View All to display the search results
        WebElement viewAllSearchResults = driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
        viewAllSearchResults.click();
        Thread.sleep(2000);
    }

    public void selectFirstSongResult () throws InterruptedException {
        // click first song in the search results
        WebElement firstSong = driver.findElement(By.cssSelector("section#songResultsWrapper tr.song-item td.title"));
        firstSong.click();
        Thread.sleep(2000);
    }

    public void clickAddToBtn () throws InterruptedException {
        // click addTo button...
        WebElement addToButton = driver.findElement(By.cssSelector("button[class='btn-add-to']"));
        addToButton.click();
        Thread.sleep(2000);
    }

    public void choosePlayList () throws InterruptedException {
        // choose the Playlist to add song too
        WebElement choosePlaylist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[@class='playlist'][normalize-space()='rrPlaylist']"));
        choosePlaylist.click();
        Thread.sleep(2000);
    }

    public String getNotificationText () {
        // Verify that the notification message appears.
        //Verify that notification message has the text, "Added 1 song into {rrPlaylist}"
        WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));
        return notificationMessage.getText();
    }
}