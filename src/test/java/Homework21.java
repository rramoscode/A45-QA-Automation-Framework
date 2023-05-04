import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{
    @Test
    public void renamePlaylist()  {

        String newPlaylistName = "rrPlaylist4";

        login("demo@class.com" , "te$t$tudent");
        doubleClickSelectPlaylist();
        doubleClickUpdatePlaylistName();
        Assert.assertTrue(newPlaylistNameExist(newPlaylistName));

    }

}
