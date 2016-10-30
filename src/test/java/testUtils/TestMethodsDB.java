package testUtils;

import utils.PropertiesHolder;
import org.junit.Assert;
import org.junit.Test;
import java.util.Properties;



/**
 * Created by work on 09.10.2016.
 */
public class TestMethodsDB {


    @Test
    public void testMethodGetProperty(){

        String expected = "src/main/resources/pathForDB.txt";
        String actual = PropertiesHolder.getProperty("PATH_FOR_SAVEDB");
        Assert.assertEquals("Test method SearchInfoByKeywordOnSite ", actual.equals(expected), true);

    }

}
