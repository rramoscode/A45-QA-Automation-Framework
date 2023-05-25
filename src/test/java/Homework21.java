import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{
    @Test
    public void renamePlaylist()  {

        String newPlaylistName = "rrPlaylist4";

        login("randy.ramos@testpro.io" , "te$t$tudent");
        doubleClickSelectPlaylist();
        doubleClickUpdatePlaylistName();
        Assert.assertTrue(newPlaylistNameExist());

    }

}
