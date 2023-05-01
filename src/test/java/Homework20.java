import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20 extends BaseTest{

    @Test
    public void deletePlaylist () {

        String deletedNotificationMsg = "Deleted playlist ";

        login("demo@class.com" , "te$t$tudent");
        playlistToBeDeleted();
        deletePlaylistRedBtn();
        Assert.assertTrue(getDeletedPlaylistMsg().contains(deletedNotificationMsg));

    }

}
