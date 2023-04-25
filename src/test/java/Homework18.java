import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework18 extends BaseTest{

@Test
public void playSong () throws InterruptedException {

    openloginUrl();
    enterEmail("demo@class.com");
    enterPassword("te$t$tudent");
    clickSubmit();
    nxtSongBtn();
    PlayButton();
   Assert.assertTrue(soundbarVisualizer());

    }
}
