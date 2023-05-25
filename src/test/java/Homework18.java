import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework18 extends BaseTest {

    @Test
    public void playSong()  {


        login("randy.ramos@testpro.io" , "te$t$tudent");
        nxtSongBtn();
        PlayButton();
        Assert.assertTrue(soundbarVisualizer());

    }
}