import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
//declaration that creates a static variable named wait of type WebDriverWait class.

    public ThreadLocal<WebDriver> threadDriver;
    public static WebDriverWait wait;
   public  static Actions actions;
   public static WebDriver driver;

// Test annotation and the helper/reusable methods
    @BeforeSuite
    static void setupClass() {

//        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
    }
    @BeforeMethod
    @Parameters ({"Hw19BaseURL"})

    public void setUpBrowser (String Hw19BaseURL) throws MalformedURLException {
//     Added ChromeOptions argument below to fix websocket error
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        driver = new ChromeDriver(options);

//        driver =  new FirefoxDriver();
        threadDriver = new ThreadLocal<>();
// myBrowser method for Pbrowser parameter for variable browser
        driver = pickBrowser(System.getProperty("browser"));
        threadDriver.set(driver);

       getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

// instance - initializes the wait variable with a new WebDriverWait object that will wait up to 10 seconds for the expected condition to be met.
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
// create the actions object and passing the driver
        actions = new Actions(getDriver());
        driver.get(Hw19BaseURL);
    }

    @AfterMethod
    public void tearDownBrowser () {
        getDriver().quit();
        threadDriver.remove();
    }

    public WebDriver getDriver() {
        return threadDriver.get();
    }
// implementing Browser Functionality when setting parameter in gradle
    private static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURl = "http://192.168.1.22:4444";
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);
            case "safari":
                WebDriverManager.safaridriver().setup();
                return driver = new SafariDriver();
            case "grid-firefox":
                caps.setCapability("browserName" , "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURl).toURL(),caps);
            case "grid-edge":
                caps.setCapability("browserName" , "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURl).toURL(),caps);
            case "grid-safari":
                caps.setCapability("browserName" , "safari");
                return driver = new RemoteWebDriver(URI.create(gridURl).toURL(),caps);
            case "grid-chrome":
                caps.setCapability("browserName" , "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURl).toURL(),caps);
            case "cloud":
                return lambdaTest();
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                return driver = new ChromeDriver(options);
        }
    }

    public static WebDriver lambdaTest() throws MalformedURLException {
     //   String username = "randy.ramos";
     //   String accessToken = "qenmTC0k43LuI5riE7m2ZaRjubuWveKtWo2cr1675mTIIzOoLw";
        String hubURL = "https://hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("113.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "randy.ramos");
        ltOptions.put("accessKey", "qenmTC0k43LuI5riE7m2ZaRjubuWveKtWo2cr1675mTIIzOoLw");
        ltOptions.put("project", "Homework25");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);
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
//        WebElement searchFeild = driver.findElement(By.cssSelector("[name='q']"));
        WebElement searchFeild = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name='q']")));
        searchFeild.sendKeys(songTitleKeyword);
//       Thread.sleep(2000);
    }

    public void clickViewAllBtn () throws InterruptedException {
        // click View All to display the search results
//        WebElement viewAllSearchResults = driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
        WebElement viewAllSearchResults = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-test='view-all-songs-btn']")));
        viewAllSearchResults.click();
//        Thread.sleep(2000);
    }

    public void selectFirstSongResult () throws InterruptedException {
        // click first song in the search results
//        WebElement firstSong = driver.findElement(By.cssSelector("section#songResultsWrapper tr.song-item td.title"));
        WebElement firstSong = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("section#songResultsWrapper tr.song-item td.title")));
        firstSong.click();
//        Thread.sleep(2000);
    }

    public void clickAddToBtn () throws InterruptedException {
        // click addTo button...
//        WebElement addToButton = driver.findElement(By.cssSelector("button[class='btn-add-to']"));
        WebElement addToButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='btn-add-to']")));
        addToButton.click();
//        Thread.sleep(2000);
    }

    public void choosePlayList () throws InterruptedException {
        // choose the Playlist to add song too
//        WebElement choosePlaylist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[@class='playlist'][normalize-space()='rrPlaylist']"));
        WebElement choosePlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//li[@class='playlist'][normalize-space()='rrPlaylist']")));
        choosePlaylist.click();
//        Thread.sleep(2000);
    }

    public String getNotificationText () {
        // /Verify that the notification message appears.
        // /Verify that notification message has the text, "Added 1 song into {rrPlaylist}"
//        WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));
        WebElement notificationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationMessage.getText();
    }

    public void nxtSongBtn () throws InterruptedException {
 //   WebElement nextSongBtn = driver.findElement(By.xpath("//i[@title='Play next song']"));
    WebElement nextSongBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@title='Play next song']")));
    nextSongBtn.click();
//    Thread.sleep(3000);

    }

    public void PlayButton () throws InterruptedException {
//    WebElement playBtn = driver.findElement(By.xpath("//span[@title='Play or resume']//i[@class='fa fa-play']"));
    WebElement playBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Play or resume']//i[@class='fa fa-play']")));
    playBtn.click();
//    Thread.sleep(3000);
    }

    public  boolean soundbarVisualizer() {
//    WebElement soundBar = driver.findElement(By.xpath("//img[@alt='Sound bars']"));
    WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Sound bars']")));
    return soundBar.isDisplayed();

    }

    public void playlistToBeDeleted () {
//        WebElement selectThePlaylist = driver.findElement(By.xpath("//a[normalize-space()='rrPlaylist2']"));
        WebElement selectThePlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='rrPlaylist2']")));
        selectThePlaylist.click();
    }

    public void deletePlaylistRedBtn () {
//        WebElement deletePlaylistBtn = driver.findElement(By.cssSelector("[title='Delete this playlist']"));
        WebElement deletePlaylistBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title='Delete this playlist']")));
        deletePlaylistBtn.click();

    }

    public String getDeletedPlaylistMsg (){
//        WebElement deletedPlaylistMsg = driver.findElement(By.cssSelector("div.success.show"));
        WebElement deletedPlaylistMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return deletedPlaylistMsg.getText();
    }

    // helper method for non-empty playlist
    public void deleteThePlaylistOk () {
        WebElement deleteConformation = driver.findElement(By.xpath("//button[normalize-space()='Ok']"));
        deleteConformation.click();

    }

    public void doubleClickSelectPlaylist()  {
        WebElement selectAPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='rrPlaylist3']")));
        actions.doubleClick(selectAPlaylist).perform();
    }

    public void doubleClickUpdatePlaylistName()  {
        WebElement playlistNameField = driver.findElement(By.cssSelector("input[name='name']"));
        actions.doubleClick(playlistNameField).perform();
        playlistNameField.sendKeys(Keys.DELETE);
        playlistNameField.sendKeys("rrPlaylist4");
        playlistNameField.sendKeys(Keys.ENTER);

    }

    public boolean newPlaylistNameExist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='rrPlaylist4']")));
        WebElement playlistElement = driver.findElement(By.xpath("//a[normalize-space()='rrPlaylist4']"));
        return playlistElement.isDisplayed();
    }
}
