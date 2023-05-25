import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework17 extends BaseTest{

    @Test
    public void addSongToPlaylist()  {

        String newSongAddedNotificationText = "Added 1 song into";

        login("randy.ramos@testpro.io" , "te$t$tudent");
        searchSong("Dark Days");
        clickViewAllBtn();
        selectFirstSongResult();
        clickAddToBtn();
        choosePlayList();
        Assert.assertTrue(getNotificationText().contains(newSongAddedNotificationText));

    }

}
