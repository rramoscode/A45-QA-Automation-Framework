import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class BaseTest {
//declaration that creates a static variable named wait of type WebDriverWait class.
    static WebDriverWait wait;
    WebDriver driver;

// Test annotation and the helper/reusable methods
    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    @Parameters ({"Hw19BaseURL"})

    public void setUpBrowser (String Hw19BaseURL) {
//     Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
// instance - initializes the wait variable with a new WebDriverWait object that will wait up to 10 seconds for the expected condition to be met.
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(Hw19BaseURL);
    }

    @AfterMethod
    public void tearDownBrowser () {
        driver.quit();
    }

    public void openloginUrl() {
        driver.get("https://bbb.testpro.io/");
    }
    public void login(String email, String password) {
// Initialize and wait till element(link) became clickable - timeout in 10 seconds

//        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
        emailField.sendKeys(email);

//       WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='password']")));
        passwordField.sendKeys(password);

//        WebElement loginButton = driver.findElement(By.cssSelector(("[type='submit']")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']")));
        loginButton.click();

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

    public void nxtSongBtn () throws InterruptedException {
    WebElement nextSongBtn = driver.findElement(By.xpath("//i[@title='Play next song']"));
    nextSongBtn.click();
    Thread.sleep(3000);

    }

    public void PlayButton () throws InterruptedException {
    WebElement playBtn = driver.findElement(By.xpath("//span[@title='Play or resume']//i[@class='fa fa-play']"));
    playBtn.click();
    Thread.sleep(3000);
    }

    public  boolean soundbarVisualizer() {
    WebElement soundBar = driver.findElement(By.xpath("//img[@alt='Sound bars']"));
    return soundBar.isDisplayed();

    }

    public void playlistToBeDeleted () {
//        WebElement selectThePlaylist = driver.findElement(By.xpath("//a[normalize-space()='rrPlaylist2']"));
        WebElement selectThePlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='rrPlaylist3']")));
        selectThePlaylist.click();
    }

    public void deletePlaylistRedBtn () {
//        WebElement deletePlaylistBtn = driver.findElement(By.cssSelector("[title='Delete this playlist']"));
        WebElement deletePlaylistBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title='Delete this playlist3']")));
        deletePlaylistBtn.click();

    }

    public String getDeletedPlaylistMsg (){
//        WebElement deletedPlaylistMsg = driver.findElement(By.cssSelector("div.success.show"));
        WebElement deletedPlaylistMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return deletedPlaylistMsg.getText();
    }

//    // helper method for non-empty playlist
//    public void deleteThePlaylistOk () {
//        WebElement deleteConformation = driver.findElement(By.xpath("//button[normalize-space()='Ok']"));
//        deleteConformation.click();
//
//    }
}
