import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20 extends BaseTest{

    @Test
    public void deletePlaylist () throws InterruptedException {

        String deletedNotificationMsg = "Deleted playlist ";

        login("demo@class.com" , "te$t$tudent");
        playlistToBeDeleted();
        deletePlaylistRedBtn();
// // method for non-empty playlist
//       deleteThePlaylistOk();

        Assert.assertTrue(getDeletedPlaylistMsg().contains(deletedNotificationMsg));

    }

}
